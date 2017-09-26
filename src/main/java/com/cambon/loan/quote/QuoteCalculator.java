package com.cambon.loan.quote;

import com.cambon.loan.lenders.Lender;

import java.util.List;

public interface QuoteCalculator {

	Quote calculateMonthlyRepayment(Integer loanAmount, double rate, Integer months);

	double computeLoanRate(List<Lender> lenders, Integer loanAmount);
}
