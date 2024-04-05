package javau9.ca.evote.controllers;


import javau9.ca.evote.dto.ElectionDto;
import javau9.ca.evote.services.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/elections")
public class ElectionController {


    ElectionService electionService;

    @Autowired
    public ElectionController(ElectionService electionService) {
        this.electionService = electionService;
    }

    @PostMapping
    public ResponseEntity<ElectionDto> createElection(@RequestBody ElectionDto electionDto) {
        ElectionDto createdElection = electionService.createElection(electionDto);
        return new ResponseEntity<>(createdElection, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ElectionDto>> getAllElections() {
        List<ElectionDto> elections = electionService.findAllElections();
        return ResponseEntity.ok(elections);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElectionDto> getElectionById(@PathVariable Long id) {
        ElectionDto electionDto = electionService.findElectionById(id);
        return ResponseEntity.ok(electionDto);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ElectionDto> updateElection(@PathVariable Long id, @RequestBody ElectionDto electionDto) {
        electionDto.setId(id);
        ElectionDto updatedElection = electionService.updateElection(electionDto);
        return ResponseEntity.ok(updatedElection);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteElection(@PathVariable Long id) {
        electionService.deleteElection(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<Void> startElection(@PathVariable Long id) {
        electionService.startElection(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/end")
    public ResponseEntity<Void> endElection(@PathVariable Long id) {
        electionService.endElection(id);
        return ResponseEntity.ok().build();
    }



}
