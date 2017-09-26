package com.cambon.loan.quote;

import com.cambon.loan.lenders.Lender;
import com.cambon.loan.lenders.LendersCsvFileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

import static com.cambon.loan.configuration.LoanConfiguration.DF_1;
import static com.cambon.loan.configuration.LoanConfiguration.LOAN_MONTHS;

public class QuoteCalculatorTest {
	private	final int SAMPLE_LOAN_AMOUNT = 1000;
	private final double SAMPLE_LOAN_RATE = 7.0d;
	private final double EXPECTED_MONTHLY_PAYMENT = 30.78d;
	private final double EXPECTED_TOTAL_PAYMENT = 1108.08d;
	private static final String MARKET_FILE = "MarketTestData.csv";
	private CompoundInterestQuoter quoteCalculator = new CompoundInterestQuoter();
	private LendersCsvFileReader lendersCsvFileReader = new LendersCsvFileReader();

	@Test
	void sampleLoanQuoteReturnsExpectedResult() {
		Quote quote = quoteCalculator.calculateMonthlyRepayment(SAMPLE_LOAN_AMOUNT, SAMPLE_LOAN_RATE, LOAN_MONTHS);
		Quote expectedQuote = new Quote(SAMPLE_LOAN_AMOUNT, SAMPLE_LOAN_RATE, EXPECTED_MONTHLY_PAYMENT, EXPECTED_TOTAL_PAYMENT);
		Assertions.assertEquals(quote.toString(), expectedQuote.toString());
	}

	@Test
	void retrievesAverageRateForLoanAmountFromLenders() throws IOException, URISyntaxException {

		List<Lender> lenders = lendersCsvFileReader.readLendersFromFile(
				Paths.get(QuoteCalculatorTest.class.getClassLoader().getResource(MARKET_FILE).toURI()));
		double rate = quoteCalculator.computeLoanRate(lenders, SAMPLE_LOAN_AMOUNT);
		Assertions.assertEquals(DF_1.format(rate), DF_1.format(SAMPLE_LOAN_RATE));
	}
}
