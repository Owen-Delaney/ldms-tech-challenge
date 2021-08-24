package owen.delaney.ldms.tech.challenge.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;

import owen.delaney.ldms.tech.challenge.entity.Loan;
import owen.delaney.ldms.tech.challenge.entity.Schedule;
import owen.delaney.ldms.tech.challenge.repository.LoanRepository;

@Service
public class AmortisationService {
	
	private final LoanRepository repository;
	
	AmortisationService(LoanRepository repository){
		this.repository = repository;
	}
	
	public Loan newLoan (Loan newLoan) {
		return repository.save(newLoan);
	}
	
	public List<Loan> allLoans () {
		return repository.findAll();
	}
	
	public Schedule amortisationSchedule (Loan newLoan) {
		return null;
		
	}
	
	BigDecimal monthlyRepayment (BigDecimal principal, 
			BigDecimal interestRate, int monthlyPayments) {
		BigDecimal monthlyInterestRate = interestRate.divide(new BigDecimal(12));
		BigDecimal one = new BigDecimal("1");
		BigDecimal sumOne = principal.multiply(monthlyInterestRate.multiply(
			monthlyInterestRate.add(one).pow(monthlyPayments)));
		BigDecimal sumTwo = one.add(monthlyInterestRate).pow(monthlyPayments).subtract(one);
	return sumOne.divide(sumTwo, 2, RoundingMode.HALF_EVEN);
		
	}
	
	BigDecimal monthlyRepayment (BigDecimal principal, 
			BigDecimal interestRate, int monthlyPayments, BigDecimal balloonPayment) {
		BigDecimal monthlyInterestRate = interestRate.divide(new BigDecimal(12));
		BigDecimal one = new BigDecimal("1");
		BigDecimal sumOne = principal.subtract(balloonPayment.divide(one.add(
				monthlyInterestRate).pow(monthlyPayments), 2, RoundingMode.HALF_EVEN));
		BigDecimal sumTwo = monthlyInterestRate.divide(one.subtract((one.add(monthlyInterestRate))
				.pow(monthlyPayments * -1, new MathContext(500, RoundingMode.HALF_EVEN))), 500, RoundingMode.HALF_EVEN);
		
	return sumOne.multiply(sumTwo).setScale(2, RoundingMode.HALF_UP);
		
	}

}
