package com.cambon.loan.validation;

import com.cambon.loan.lenders.LendersCsvFileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoanValidatorTest {

	@Test
	void testLoanAmountMustBeHigherThan999() {

		Assertions.assertFalse(LoanValidator.validateLoanAmount("500"));
	}

	@Test
	void loanAmountMustBeLowerThan15001() {

		Assertions.assertFalse(LoanValidator.validateLoanAmount("15500"));
	}

	@Test
	void loanAmountMustBeAnInteger() {

		Assertions.assertFalse(LoanValidator.validateLoanAmount("1500.00"));
	}

	@Test
	void loanAmountMustBeAMultipleOf100() {

		Assertions.assertFalse(LoanValidator.validateLoanAmount("1501"));
	}

	@Test
	void loanAmountIsCorrect() {

		Assertions.assertTrue(LoanValidator.validateLoanAmount("1000"));
		Assertions.assertTrue(LoanValidator.validateLoanAmount("1500"));
		Assertions.assertTrue(LoanValidator.validateLoanAmount("15000"));
	}

}
