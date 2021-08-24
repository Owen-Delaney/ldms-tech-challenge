package owen.delaney.ldms.tech.challenge.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import owen.delaney.ldms.tech.challenge.entity.Loan;
import owen.delaney.ldms.tech.challenge.entity.Payment;
import owen.delaney.ldms.tech.challenge.entity.Schedule;
import owen.delaney.ldms.tech.challenge.exception.LoanNotFoundException;
import owen.delaney.ldms.tech.challenge.repository.LoanRepository;

@Service
public class AmortisationService {
	
	private final LoanRepository repository;
	
	AmortisationService(LoanRepository repository){
		this.repository = repository;
	}
	
	public Loan saveLoan (Loan newLoan) {
		return repository.save(newLoan);
	}
	
	public List<Loan> allLoans () {
		return repository.findAll();
	}
	
	public Schedule getLoanAmortisationSchedule(Long id) {
		Loan loan = repository.findById(id)
				.orElseThrow(() -> new LoanNotFoundException(id));
		return amortisationSchedule(loan);
	}
	
	public Schedule amortisationSchedule (Loan newLoan) {
		List<Payment> payments = new ArrayList<>();
		BigDecimal payment;
		BigDecimal remainingBalance = newLoan.getAssetValue().subtract(newLoan.getDeposit());
		
		
		if (newLoan.getBallonPayment() != null) {
			payment = monthlyRepayment(remainingBalance, 
					newLoan.getInterestRate(), newLoan.getMonthlyPayments(),
					newLoan.getBallonPayment());
		} else {
			payment = monthlyRepayment(remainingBalance, 
					newLoan.getInterestRate(), newLoan.getMonthlyPayments());
		}
		
		for (int i = 1; i <= newLoan.getMonthlyPayments(); i++) {
			BigDecimal interest = interestPayment(remainingBalance, 
					newLoan.getInterestRate());
			remainingBalance = remainingBalance.subtract(payment.subtract(interest));
			payments.add(new Payment(i, payment, principalPayment(payment, 
					interest), interest, remainingBalance));
		}
		
		return new Schedule(payments, saveLoan(newLoan));		
		
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
	
	BigDecimal interestPayment (BigDecimal remainingBalance, BigDecimal interestRate) {
		BigDecimal monthlyInterestRate = interestRate.divide(new BigDecimal(12));
		return remainingBalance.multiply(monthlyInterestRate).setScale(2, RoundingMode.HALF_UP);
	}
	
	BigDecimal principalPayment (BigDecimal totalPayment, BigDecimal interestPayment) {
		return totalPayment.subtract(interestPayment).setScale(2, RoundingMode.HALF_UP);
	}

}
