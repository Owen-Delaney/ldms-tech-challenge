package owen.delaney.ldms.tech.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import owen.delaney.ldms.tech.challenge.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

}

