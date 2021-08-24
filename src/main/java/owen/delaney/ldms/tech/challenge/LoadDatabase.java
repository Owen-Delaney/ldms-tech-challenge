package owen.delaney.ldms.tech.challenge;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import owen.delaney.ldms.tech.challenge.entity.Loan;
import owen.delaney.ldms.tech.challenge.repository.LoanRepository;

@Configuration
public class LoadDatabase {
	
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	
	@Bean
	CommandLineRunner initDatabase(LoanRepository repository) {

	  return args -> {
	    log.info("Preloading test data" + repository.save(new Loan(
	    		new BigDecimal("25000.00"), new BigDecimal("5000.00"), 
	    		new BigDecimal("0.075"), 36, null)));
	    log.info("Preloading test data" + repository.save(new Loan(
	    		new BigDecimal("50000.00"), new BigDecimal("10000.00"), 
	    		new BigDecimal("0.065"), 36, new BigDecimal("10000.00"))));
	    };
	}
}


