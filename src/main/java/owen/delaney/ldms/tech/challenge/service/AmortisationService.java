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
		return new Schedule(amortisationSchedule(loan), loan);
	}
	
	public Schedule newLoan(Loan newLoan) {
		return new Schedule(amortisationSchedule(newLoan), saveLoan(newLoan));
	}
	
	private List<Payment> amortisationSchedule (Loan loan) {
		List<Payment> payments = new ArrayList<>();
		BigDecimal payment;
		BigDecimal remainingBalance = loan.getAssetValue().subtract(loan.getDeposit());
		
		
		if (loan.getBallonPayment() != null) {
			payment = monthlyRepayment(remainingBalance, 
					loan.getInterestRate(), loan.getMonthlyPayments(),
					loan.getBallonPayment());
		} else {
			payment = monthlyRepayment(remainingBalance, 
					loan.getInterestRate(), loan.getMonthlyPayments());
		}
		
		for (int i = 1; i <= loan.getMonthlyPayments(); i++) {
			BigDecimal interest = interestPayment(remainingBalance, 
					loan.getInterestRate());
			remainingBalance = remainingBalance.subtract(payment.subtract(interest));
			payments.add(new Payment(i, payment, principalPayment(payment, 
					interest), interest, remainingBalance));
		}
		
		return payments;		
		 
	}
	
	private BigDecimal monthlyRepayment (BigDecimal principal, 
			BigDecimal interestRate, int monthlyPayments) {
		BigDecimal monthlyInterestRate = interestRate.divide(new BigDecimal(12));
		BigDecimal one = new BigDecimal("1");
		BigDecimal sumOne = principal.multiply(monthlyInterestRate.multiply(
			monthlyInterestRate.add(one).pow(monthlyPayments)));
		BigDecimal sumTwo = one.add(monthlyInterestRate).pow(monthlyPayments).subtract(one);
	return sumOne.divide(sumTwo, 2, RoundingMode.HALF_EVEN);
		
	}
	
	private BigDecimal monthlyRepayment (BigDecimal principal, 
			BigDecimal interestRate, int monthlyPayments, BigDecimal balloonPayment) {
		BigDecimal monthlyInterestRate = interestRate.divide(new BigDecimal(12));
		BigDecimal one = new BigDecimal("1");
		BigDecimal sumOne = principal.subtract(balloonPayment.divide(one.add(
				monthlyInterestRate).pow(monthlyPayments), 2, RoundingMode.HALF_EVEN));
		BigDecimal sumTwo = monthlyInterestRate.divide(one.subtract((one.add(monthlyInterestRate))
				.pow(monthlyPayments * -1, new MathContext(1000, RoundingMode.HALF_EVEN))), 1000, RoundingMode.HALF_EVEN);
		
	return sumOne.multiply(sumTwo).setScale(2, RoundingMode.HALF_UP);
		
	}
	
	private BigDecimal interestPayment (BigDecimal remainingBalance, BigDecimal interestRate) {
		BigDecimal monthlyInterestRate = interestRate.divide(new BigDecimal(12));
		return remainingBalance.multiply(monthlyInterestRate).setScale(2, RoundingMode.HALF_UP);
	}
	
	private BigDecimal principalPayment (BigDecimal totalPayment, BigDecimal interestPayment) {
		return totalPayment.subtract(interestPayment).setScale(2, RoundingMode.HALF_EVEN);
	}

}
