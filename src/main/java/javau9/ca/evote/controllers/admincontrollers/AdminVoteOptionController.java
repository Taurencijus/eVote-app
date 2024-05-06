package javau9.ca.evote.controllers.admincontrollers;

import jakarta.validation.Valid;
import javau9.ca.evote.dto.VoteOptionDto;
import javau9.ca.evote.services.VoteOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/admin_only/api/vote-options")
public class AdminVoteOptionController {

    VoteOptionService voteOptionService;

    @Autowired
    public AdminVoteOptionController(VoteOptionService voteOptionService) {
        this.voteOptionService = voteOptionService;
    }

    @PostMapping("/by-election/{electionId}")
    public ResponseEntity<VoteOptionDto> createVoteOptionByElectionId(@PathVariable Long electionId, @Valid @RequestBody VoteOptionDto voteOptionDto) {
        voteOptionDto.setElectionId(electionId);
        VoteOptionDto createdVoteOption = voteOptionService.createVoteOption(voteOptionDto);
        return new ResponseEntity<>(createdVoteOption, HttpStatus.CREATED);
    }

    @PutMapping("/by-election/{id}")
    public ResponseEntity<VoteOptionDto> updateVoteOptionByElectionId(@Valid @PathVariable Long id,
                                                                      @RequestBody VoteOptionDto voteOptionDto) {
        voteOptionDto.setId(id);
        VoteOptionDto updatedVoteOption = voteOptionService.updateVoteOption(voteOptionDto);
        return ResponseEntity.ok(updatedVoteOption);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoteOptionById(@PathVariable Long id) {
        voteOptionService.deleteVoteOption(id);
        return ResponseEntity.noContent().build();
    }
}
