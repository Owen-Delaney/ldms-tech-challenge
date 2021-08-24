package owen.delaney.ldms.tech.challenge.entity;

import java.util.List;

public class Schedule {
	
	private Loan loan;
	private List<Payment> payments;

	public List<Payment> getPayments() {
		return payments;
	}
	
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	
	public Loan getLoan() {
		return loan;
	}
	
	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	
	public Schedule (List<Payment> payments, Loan loan) {
		this.loan = loan;
		this.payments = payments;
	}
	
}


