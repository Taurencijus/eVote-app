package javau9.ca.evote.repositories;

import javau9.ca.evote.models.VoteOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteOptionRepository extends JpaRepository<VoteOption, Long> {

    List<VoteOption> findByElectionId(Long electionId);
}
