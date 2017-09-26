package com.cambon.loan.lenders;

public class Lender {

	private String lender;
	private double rate;
	private Integer available;

	public Lender(String lender, double rate, Integer available) {

		this.lender = lender;
		this.rate = rate;
		this.available = available;
	}

	public double getRate() {

		return rate;
	}

	public Integer getAvailable() {

		return available;
	}

	@Override
	public String toString() {

		return "Lender{" +
				"lender='" + lender + '\'' +
				", rate=" + rate +
				", available=" + available +
				'}';
	}
}
