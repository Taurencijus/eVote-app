package javau9.ca.evote.controllers.admincontrollers;

import javau9.ca.evote.dto.VoteDto;
import javau9.ca.evote.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/admin_only/api/votes")
public class AdminVoteController {

    VoteService voteService;

    @Autowired
    public AdminVoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping("/by-election/{electionId}")
    public ResponseEntity<List<VoteDto>> getAllVotesByElectionId(@PathVariable Long electionId) {
        List<VoteDto> votes = voteService.findAllVotesByElectionId(electionId);
        return ResponseEntity.ok(votes);
    }
}
