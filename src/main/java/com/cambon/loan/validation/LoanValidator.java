package com.cambon.loan.validation;

import static com.cambon.loan.configuration.LoanConfiguration.LOAN_AMOUNT_INCREMENT;
import static com.cambon.loan.configuration.LoanConfiguration.MAX_LOAN_AMOUNT;
import static com.cambon.loan.configuration.LoanConfiguration.MIN_LOAN_AMOUNT;

public class LoanValidator {
	public static boolean validateLoanAmount(String loanAmount) {

		if (loanAmount != null) {
			try {
				Integer requestedLoanAmount = Integer.valueOf(loanAmount);
				if (requestedLoanAmount >= MIN_LOAN_AMOUNT && requestedLoanAmount <= MAX_LOAN_AMOUNT
						&& requestedLoanAmount % LOAN_AMOUNT_INCREMENT == 0) {
					return true;
				}
			} catch (NumberFormatException ignore) {
			}
		}

		return false;
	}
}
