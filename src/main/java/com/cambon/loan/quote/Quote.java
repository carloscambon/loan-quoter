package com.cambon.loan.quote;

import static com.cambon.loan.configuration.LoanConfiguration.DF_1;
import static com.cambon.loan.configuration.LoanConfiguration.DF_2;

public class Quote {

	private int requestedAmount;
	private double rate;
	private double monthlyRepayment;
	private double totalPayment;

	public Quote(int requestedAmount, double rate, double monthlyRepayment, double totalPayment) {

		this.requestedAmount = requestedAmount;
		this.rate = rate;
		this.monthlyRepayment = monthlyRepayment;
		this.totalPayment = totalPayment;
	}

	@Override
	public String toString() {

		return
				"Requested amount: &" + requestedAmount +
				"\nRate: " + DF_1.format(rate) + "%" +
				"\nMonthly repayment: &" + DF_2.format(monthlyRepayment) +
				"\nTotal payment: &" + DF_2.format(totalPayment);
	}
}
