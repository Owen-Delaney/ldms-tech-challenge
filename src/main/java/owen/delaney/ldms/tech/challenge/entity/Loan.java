package owen.delaney.ldms.tech.challenge.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.lang.Nullable;

@Entity
public class Loan {
	private @Id @GeneratedValue Long id;
	private BigDecimal assetValue;
	private BigDecimal deposit;
	@Column(precision = 7, scale = 5)
	private BigDecimal interestRate;
	private Integer monthlyPayments;
	@Nullable
	private BigDecimal ballonPayment;
	
	public Loan() {}
	
	public Loan (BigDecimal assetValue, BigDecimal deposit, BigDecimal interestRate, 
			Integer monthlyPayments, BigDecimal ballonPayment) {
		this.setAssetValue(assetValue);
		this.setDeposit(deposit);
		this.setInterestRate(interestRate);
		this.setMonthlyPayments(monthlyPayments);
		this.setBallonPayment(((ballonPayment != null) ? ballonPayment : null));
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAssetValue() {
		return assetValue;
	}

	public void setAssetValue(BigDecimal assetValue) {
		this.assetValue = assetValue;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate.setScale(5, RoundingMode.HALF_UP);
	}

	public Integer getMonthlyPayments() {
		return monthlyPayments;
	}

	public void setMonthlyPayments(Integer monthlyPayments) {
		this.monthlyPayments = monthlyPayments;
	}

	public BigDecimal getBallonPayment() {
		return ballonPayment;
	}

	public void setBallonPayment(BigDecimal ballonPayment) {
		this.ballonPayment = ballonPayment;
	}
	
}
