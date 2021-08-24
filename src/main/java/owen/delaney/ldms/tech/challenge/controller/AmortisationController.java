package owen.delaney.ldms.tech.challenge.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import owen.delaney.ldms.tech.challenge.entity.Loan;
import owen.delaney.ldms.tech.challenge.entity.Schedule;
import owen.delaney.ldms.tech.challenge.service.AmortisationService;

@RestController
public class AmortisationController {
	
	private final AmortisationService service;
	
	AmortisationController(AmortisationService service){
		this.service = service;
	}
	
	@PostMapping("/newLoan")
	public Schedule newLoan(@RequestBody Loan newLoan) {
		return service.amortisationSchedule(newLoan);
	}
	
	@GetMapping("/allLoans")
	public List<Loan> allLoans() {
		return service.allLoans();
	}
	
	@GetMapping("/loan/{id}")
	public Schedule getLoanById (Long id) {
		return service.getLoanAmortisationSchedule(id);
	}
	
	
}
