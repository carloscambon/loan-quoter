package com.cambon.loan.quote;

import com.cambon.loan.lenders.Lender;

import java.util.ArrayList;
import java.util.List;

import static com.cambon.loan.configuration.LoanConfiguration.DF_2;

public class CompoundInterestQuoter implements QuoteCalculator {

	@Override
	public Quote calculateMonthlyRepayment(Integer loanAmount, double rate, Integer months) {

		double effectiveRate = Math.pow(1 + rate / 100, 1d / 12) - 1;
		double monthlyRepayment = loanAmount * (effectiveRate * Math.pow(1 + effectiveRate, months))
				/ (Math.pow(1 + effectiveRate, months) - 1);
		monthlyRepayment = Double.valueOf(DF_2.format(monthlyRepayment));
		return new Quote(loanAmount, rate, monthlyRepayment, months * monthlyRepayment);
	}

	@Override
	public double computeLoanRate(List<Lender> lenders, Integer loanAmount) {

		List<Lender> selectedLendersForLoan = new ArrayList<>();
		double accumulatedLoanSum = 0d;
		for (Lender lender : lenders) {
			selectedLendersForLoan.add(lender);
			accumulatedLoanSum = accumulatedLoanSum + lender.getAvailable();
			if (accumulatedLoanSum >= loanAmount) {
				break;
			}
		}
		return selectedLendersForLoan.stream().mapToDouble(lender -> lender.getRate()).average().getAsDouble() * 100;
	}

}
