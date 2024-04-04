package javau9.ca.evote.repositories;

import javau9.ca.evote.models.VoteOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteOptionRepository extends JpaRepository<VoteOption, Long> {
}
