package owen.delaney.ldms.tech.challenge.entity;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.lang.Nullable;

@Entity
public class Loan {
	private @Id @GeneratedValue Long id;
	private BigDecimal assetValue;
	private BigDecimal deposit;
	private BigDecimal interestRate;
	private Integer monthlyPayments;
	@Nullable
	private BigDecimal ballonPayment;
	
	Loan() {}
	
	Loan (BigDecimal assetValue, BigDecimal deposit, BigDecimal interestRate, 
			Integer monthlyPayments, BigDecimal ballonPayment) {
		this.assetValue = assetValue;
		this.deposit = deposit;
		this.interestRate = interestRate;
		this.monthlyPayments = monthlyPayments;
		this.ballonPayment = ((ballonPayment != null) ? ballonPayment : null);
	}
	
	  @Override
	  public String toString() {
	    return "Loan{" + "id=" + this.id 
	    		+ ", assetValue='" + this.assetValue + '\'' 
	    		+ ", deposit='" + this.deposit + '\'' 
	    		+ ", interestRate='" + this.interestRate + '\'' 
	    		+ ", monthlyPayments='" + this.monthlyPayments + '\'' 
	    		+ ", ballonPayment='" + this.ballonPayment + '\'' + '}';
	  }
	
}
