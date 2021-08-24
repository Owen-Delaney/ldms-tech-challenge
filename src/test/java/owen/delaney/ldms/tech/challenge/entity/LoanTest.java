package owen.delaney.ldms.tech.challenge.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class LoanTest {
	private BigDecimal assetValue = new BigDecimal("25000.00");
	private BigDecimal deposit = new BigDecimal("5000.00");
	private BigDecimal interestRate = new BigDecimal("7.5");
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
	
	@Test
	void test_should_return_the_loan_as_a_string() {
		String expectedValue = "Loan{id=null, assetValue='25000.00', "
				+ "deposit='5000.00', interestRate='7.5', monthlyPayments='36', "
				+ "ballonPayment='null'}";
		Loan loan = new Loan(assetValue, deposit, interestRate, monthlyPayments, 
				null);
		assertEquals(expectedValue, loan.toString());
	}

}
