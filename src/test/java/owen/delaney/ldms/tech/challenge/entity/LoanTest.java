package owen.delaney.ldms.tech.challenge.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class LoanTest {
	private BigDecimal assetValue = new BigDecimal("25000.00");
	private BigDecimal deposit = new BigDecimal("5000.00");
	private BigDecimal interestRate = new BigDecimal("0.0075");
	private Integer monthlyPayments = 36;
	
	
    
	@Test
	void test_should_create_a_loan_with_a_ballon_payment() {
		BigDecimal ballonPayment = new BigDecimal("10000.00");
		Loan loan = new Loan(assetValue, deposit, interestRate, monthlyPayments, 
				ballonPayment);
		assertNotNull(loan);
	}
	
	@Test
	void test_should_create_a_loan_without_a_ballon_payment() {
		Loan loan = new Loan(assetValue, deposit, interestRate, monthlyPayments, 
				null);
		assertNotNull(loan);
	}	

}
