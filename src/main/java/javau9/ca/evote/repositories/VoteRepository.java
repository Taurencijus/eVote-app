package javau9.ca.evote.repositories;

import javau9.ca.evote.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
