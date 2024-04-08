package javau9.ca.evote.controllers;


import javau9.ca.evote.dto.VoteDto;
import javau9.ca.evote.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/votes")
public class VoteController {

    VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<VoteDto> castVote(@RequestBody VoteDto voteDto) {
        VoteDto castedVote = voteService.castVote(voteDto);
        return new ResponseEntity<>(castedVote, HttpStatus.CREATED);
    }

    @GetMapping("/election/{electionId}")
    public ResponseEntity<List<VoteDto>> getAllVotesByElectionId(@PathVariable Long electionId) {
        List<VoteDto> votes = voteService.findAllVotesByElectionId(electionId);
        return ResponseEntity.ok(votes);
    }

    @GetMapping("/count/election/{electionId}")
    public ResponseEntity<Map<Long, Integer>> countVotesByElectionId(@PathVariable Long electionId) {
        Map<Long, Integer> voteCounts = voteService.countVotesByElectionId(electionId);
        return ResponseEntity.ok(voteCounts);
    }

    @DeleteMapping("/{voteId}")
    public ResponseEntity<Void> deleteVote(@PathVariable Long voteId) {
        voteService.deleteVote(voteId);
        return ResponseEntity.noContent().build();
    }

}
