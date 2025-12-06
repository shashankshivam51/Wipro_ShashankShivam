package com.myfinbank.customer.util;

public class FinanceUtils {

    /**
     * Calculate monthly EMI rounded to 2 decimals.
     * Formula: EMI = P * r * (1+r)^n / ((1+r)^n - 1)
     * r = monthly interest rate (decimal), n = months
     */
    public static double calculateEmi(double principal, double annualInterestRate, int months) {
        if (principal <= 0) throw new IllegalArgumentException("principal must be > 0");
        if (months <= 0) throw new IllegalArgumentException("months must be > 0");
        double monthlyRate = annualInterestRate / 12.0 / 100.0;
        if (monthlyRate == 0) {
            double simple = principal / months;
            return Math.round(simple * 100.0) / 100.0;
        }
        double factor = Math.pow(1 + monthlyRate, months);
        double emi = principal * monthlyRate * factor / (factor - 1);
        return Math.round(emi * 100.0) / 100.0;
    }
}
