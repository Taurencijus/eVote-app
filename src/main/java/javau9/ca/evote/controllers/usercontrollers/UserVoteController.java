package javau9.ca.evote.controllers.usercontrollers;

import jakarta.validation.Valid;
import javau9.ca.evote.dto.VoteDto;
import javau9.ca.evote.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/votes")
public class UserVoteController {

    VoteService voteService;

    @Autowired
    public UserVoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<VoteDto> castVote(@Valid @RequestBody VoteDto voteDto) {
        VoteDto castedVote = voteService.castVote(voteDto);
        return new ResponseEntity<>(castedVote, HttpStatus.CREATED);
    }

    @DeleteMapping("/{voteId}")
    public ResponseEntity<Void> deleteVoteById(@PathVariable Long voteId) {
        voteService.deleteVote(voteId);
        return ResponseEntity.noContent().build();
    }
}
