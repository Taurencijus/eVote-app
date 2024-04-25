package javau9.ca.evote.controllers;


import jakarta.validation.Valid;
import javau9.ca.evote.dto.VoteOptionDto;
import javau9.ca.evote.services.VoteOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/vote-options")
public class VoteOptionController {

    VoteOptionService voteOptionService;

    @Autowired
    public VoteOptionController(VoteOptionService voteOptionService) {
        this.voteOptionService = voteOptionService;
    }

    @PostMapping
    public ResponseEntity<VoteOptionDto> createVoteOption(@Valid @RequestBody VoteOptionDto voteOptionDto) {
        VoteOptionDto createdVoteOption = voteOptionService.createVoteOption(voteOptionDto);
        return new ResponseEntity<>(createdVoteOption, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VoteOptionDto>> getAllVoteOptions() {
        List<VoteOptionDto> voteOptions = voteOptionService.findAllVoteOptions();
        return ResponseEntity.ok(voteOptions);
    }

    @GetMapping("/election/{electionId}")
    public ResponseEntity<List<VoteOptionDto>> getVoteOptionsByElectionId(@PathVariable Long electionId) {
        List<VoteOptionDto> voteOptions = voteOptionService.findVoteOptionsByElectionId(electionId);
        return ResponseEntity.ok(voteOptions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoteOptionDto> updateVoteOption(@Valid @PathVariable Long id,
                                                          @RequestBody VoteOptionDto voteOptionDto) {
        voteOptionDto.setId(id);
        VoteOptionDto updatedVoteOption = voteOptionService.updateVoteOption(voteOptionDto);
        return ResponseEntity.ok(updatedVoteOption);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoteOption(@PathVariable Long id) {
        voteOptionService.deleteVoteOption(id);
        return ResponseEntity.noContent().build();
    }


}
