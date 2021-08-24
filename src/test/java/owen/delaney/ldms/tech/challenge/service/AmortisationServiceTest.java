package owen.delaney.ldms.tech.challenge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import owen.delaney.ldms.tech.challenge.repository.LoanRepository;

class AmortisationServiceTest {
	
	private static AmortisationService amortisationService;
	
    @BeforeAll
    public static void setup() {
    	amortisationService = new AmortisationService(mock(LoanRepository.class));
    }

	@Test
	void test_monthly_repayment_calculation() {
		BigDecimal principal = new BigDecimal("20000");
		BigDecimal interestRate = new BigDecimal("0.075");
		int monthlyRepayments = 60;
		
		BigDecimal expectedResult = new BigDecimal("400.76");
		
		BigDecimal actualResult = amortisationService.monthlyRepayment(principal, 
				interestRate, monthlyRepayments);
		
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void test_monthly_repayment_calculation_with_balloon_payment() {		
		BigDecimal principal = new BigDecimal("20000");
		BigDecimal interestRate = new BigDecimal("0.075");
		int monthlyRepayments = 60;
		BigDecimal ballonPayment = new BigDecimal("10000");
		
		BigDecimal expectedResult = new BigDecimal("262.88");
		
		BigDecimal actualResult = amortisationService.monthlyRepayment(principal, 
				interestRate, monthlyRepayments, ballonPayment);
		
		assertEquals(expectedResult, actualResult);
	}
	
	
	@Test
	void test_interest_payment_calcualtion() {
		BigDecimal remainingBalance = new BigDecimal("20000");
		BigDecimal interestRate = new BigDecimal("0.075");
		
		BigDecimal expectedResult = new BigDecimal("125.00");
		
		BigDecimal actualResult = amortisationService.interestPayment(
				remainingBalance, interestRate);
		
		assertEquals(expectedResult, actualResult);
				
	}
	
	@Test
	void test_principal_payment_calcualtion() {
		BigDecimal remainingBalance = new BigDecimal("262.88");
		BigDecimal interestPayment = new BigDecimal("125");
		
		BigDecimal expectedResult = new BigDecimal("137.88");
		
		BigDecimal actualResult = amortisationService.principalPayment(
				remainingBalance, interestPayment);
		
		assertEquals(expectedResult, actualResult);
				
	}
	

}
