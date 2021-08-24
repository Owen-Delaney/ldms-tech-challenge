package owen.delaney.ldms.tech.challenge.entity;

import java.math.BigDecimal;

public class Payment {

	private int period;
	private BigDecimal payment;
	private BigDecimal principal;
	private BigDecimal intrest;
	private BigDecimal balance;
	
	public Payment(int period, BigDecimal payment, BigDecimal principal, 
			BigDecimal intrest, BigDecimal balance) {
		this.setPeriod(period);
		this.setPayment(payment);
		this.setPrincipal(principal);
		this.setIntrest(intrest);
		this.setBalance(balance);
		
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	public BigDecimal getPrincipal() {
		return principal;
	}

	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}

	public BigDecimal getIntrest() {
		return intrest;
	}

	public void setIntrest(BigDecimal intrest) {
		this.intrest = intrest;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
}
