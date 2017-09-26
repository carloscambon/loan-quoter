package com.cambon.loan.console;

import com.cambon.loan.lenders.Lender;
import com.cambon.loan.lenders.LendersCsvFileReader;
import com.cambon.loan.quote.Quote;
import com.cambon.loan.quote.CompoundInterestQuoter;

import java.io.IOException;
import java.util.List;

import static com.cambon.loan.configuration.LoanConfiguration.LOAN_MONTHS;
import static com.cambon.loan.validation.LoanValidator.validateLoanAmount;

public class LoanQuoter {

	private static CompoundInterestQuoter quoteCalculator = new CompoundInterestQuoter();
	private static LendersCsvFileReader lendersCsvFileReader = new LendersCsvFileReader();

	public static void main(String[] args) {

		if (args.length < 2) {
			printHelpAndExit();
		}
		String marketFile = args[0];
		String requestedAmount = args[1];
		if (!lendersCsvFileReader.existsFile(marketFile)) {
			System.out.println("Market file not found!");
			printHelpAndExit();
		} else if (!validateLoanAmount(requestedAmount)) {
			System.out.println("Loan amount must be between 1000 and 15000 both inclusive. And must be multiple of 100.");
			printHelpAndExit();
		} else {
			try {
				orchestrateLoanQuote(marketFile, requestedAmount);
			} catch (NumberFormatException e) {
				System.out.println("Market file contains incorrect format: " + e.getLocalizedMessage());
			} catch (Throwable e) {
				e.printStackTrace();
				System.out.println("Unexpected error: " + e.getLocalizedMessage());
				System.exit(-1);
			}
		}
	}

	public static void orchestrateLoanQuote(String marketFile, String requestedAmount) throws IOException {

		List<Lender> lenders = lendersCsvFileReader.readLendersFromFile(marketFile);
		Integer loanAmount = Integer.valueOf(requestedAmount);
		boolean enoughCashAvailable = lenders.stream().mapToInt(lender -> lender.getAvailable()).sum() >= loanAmount;
		if (!enoughCashAvailable) {
			System.out.println("It is not possible to provide a quote at this time.");
			printHelpAndExit();
		} else {
			double rate = quoteCalculator.computeLoanRate(lenders, loanAmount);
			Quote calculationResult = quoteCalculator.calculateMonthlyRepayment(loanAmount, rate, LOAN_MONTHS);
			System.out.println(calculationResult);
		}
	}

	private static void printHelpAndExit() {

		System.out.println("\n*************************************");
		System.out.println("Loan Rate Calculator usage:");
		System.out.println("LoanQuoter market_file loan_amount");
		System.out.println("- Provide the file path of the market file");
		System.out.println("- Loan amount must be between 1000 and 15000 both inclusive. And multiple of 100.");
		System.out.println("*************************************");
		System.exit(1);
	}

}
