package javau9.ca.evote.controllers.usercontrollers;

import javau9.ca.evote.dto.VoteOptionDto;
import javau9.ca.evote.services.VoteOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/vote-options")
public class UserVoteOptionController {

    VoteOptionService voteOptionService;

    @Autowired
    public UserVoteOptionController(VoteOptionService voteOptionService) {
        this.voteOptionService = voteOptionService;
    }

    @GetMapping
    public ResponseEntity<List<VoteOptionDto>> getAllVoteOptions() {
        List<VoteOptionDto> voteOptions = voteOptionService.findAllVoteOptions();
        return ResponseEntity.ok(voteOptions);
    }

    @GetMapping("/by-election/{electionId}")
    public ResponseEntity<List<VoteOptionDto>> getAllVoteOptionsByElectionId(@PathVariable Long electionId) {
        List<VoteOptionDto> voteOptions = voteOptionService.findVoteOptionsByElectionId(electionId);
        return ResponseEntity.ok(voteOptions);
    }


}
