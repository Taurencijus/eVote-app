package javau9.ca.evote.repositories;

import javau9.ca.evote.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    boolean existsByUserIdAndVoteOption_Election_Id(Long userId, Long electionId);

    boolean existsByUserIdAndElectionId(Long userId, Long electionId);
    @Query("SELECT v FROM Vote v WHERE v.voteOption.election.id = :electionId")
    List<Vote> findByElectionId(@Param("electionId") Long electionId);

    @Query("SELECT v.voteOption.id, COUNT(v) FROM Vote v WHERE v.voteOption.election.id = :electionId GROUP BY v.voteOption.id")
    List<Object[]> countVotesByElectionId(@Param("electionId") Long electionId);
}
