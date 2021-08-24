package owen.delaney.ldms.tech.challenge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import owen.delaney.ldms.tech.challenge.entity.Loan;
import owen.delaney.ldms.tech.challenge.entity.Payment;
import owen.delaney.ldms.tech.challenge.repository.LoanRepository;

class AmortisationServiceTest {
	
	private static AmortisationService amortisationService;
	private static Loan loanWithoutBalloon;
	private static Loan loanWithBalloon;
	
    @BeforeAll
    public static void setup() {
    	amortisationService = new AmortisationService(mock(LoanRepository.class));
    	loanWithoutBalloon = new Loan(new BigDecimal("25000"), 
    			new BigDecimal("5000"), 
    			new BigDecimal("0.075"), 60, null);
    	
    	loanWithBalloon = new Loan(new BigDecimal("25000"), 
    			new BigDecimal("5000"), 
    			new BigDecimal("0.075"), 60, new BigDecimal("10000"));
    }
	
	@Test
	void test_monthly_repayment_calculation1() {
		BigDecimal expectedResult = new BigDecimal("400.76");
		
		BigDecimal actualResult = amortisationService.newLoan(loanWithoutBalloon)
				.getPayments().get(0).getPayment();
		
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void test_monthly_repayment_calculation_with_balloon_payment1() {	
		BigDecimal expectedResult = new BigDecimal("262.88");
		
		BigDecimal actualResult = amortisationService.newLoan(loanWithBalloon)
				.getPayments().get(0).getPayment();
		
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void test_interest_payment_calcualtion1() {	
		BigDecimal expectedResult = new BigDecimal("125.00");
		
		BigDecimal actualResult = amortisationService.newLoan(loanWithoutBalloon)
				.getPayments().get(0).getIntrest();
		
		assertEquals(expectedResult, actualResult);
				
	}
		
	@Test
	void test_principal_payment_calcualtion1() {
		BigDecimal expectedResult = new BigDecimal("275.76");
		
		Payment payment = amortisationService.newLoan(loanWithoutBalloon).getPayments().get(0);
		BigDecimal actualResult = payment.getPayment().subtract(payment.getIntrest());
		
		assertEquals(expectedResult, actualResult);
				
	}
	

}
