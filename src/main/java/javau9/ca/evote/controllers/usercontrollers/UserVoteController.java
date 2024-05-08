package javau9.ca.evote.controllers.usercontrollers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import javau9.ca.evote.dto.VoteDto;
import javau9.ca.evote.exceptions.EntityNotFoundException;
import javau9.ca.evote.models.User;
import javau9.ca.evote.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/vote")
public class UserVoteController {

    VoteService voteService;

    @Autowired
    public UserVoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/{electionId}/{voteOptionId}")
    public ResponseEntity<?> castVote(@PathVariable Long electionId, @PathVariable Long voteOptionId, @AuthenticationPrincipal User user) {
        try {
            VoteDto castedVote = voteService.castVote(user.getId(), voteOptionId, electionId);
            return new ResponseEntity<>(castedVote, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{voteId}")
    public ResponseEntity<Void> deleteVoteById(@PathVariable Long voteId) {
        voteService.deleteVote(voteId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count/by-election/{electionId}")
    public ResponseEntity<Map<Long, Integer>> countVotesByElectionId(@PathVariable Long electionId) {
        Map<Long, Integer> voteCounts = voteService.countVotesByElectionId(electionId);
        return ResponseEntity.ok(voteCounts);
    }
}
