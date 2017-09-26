package com.cambon.loan.configuration;

import java.text.DecimalFormat;

public class LoanConfiguration {
	public static final DecimalFormat DF_1 = new DecimalFormat("0.0");
	public static final DecimalFormat DF_2 = new DecimalFormat("0.00");
	public static final int MIN_LOAN_AMOUNT = 1000;
	public static final int MAX_LOAN_AMOUNT = 15000;
	public static final int LOAN_AMOUNT_INCREMENT = 100;
	public static final Integer LOAN_MONTHS = 36;
}
