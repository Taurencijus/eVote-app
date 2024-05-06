package javau9.ca.evote.controllers.admincontrollers;

import jakarta.validation.Valid;
import javau9.ca.evote.dto.ElectionDto;
import javau9.ca.evote.services.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/admin_only/api/elections")
public class AdminElectionController {

    ElectionService electionService;

    @Autowired
    public AdminElectionController(ElectionService electionService) {
        this.electionService = electionService;
    }
    @PostMapping
    public ResponseEntity<ElectionDto> createElection(@RequestBody ElectionDto electionDto) {
        ElectionDto createdElection = electionService.createElection(electionDto);
        return new ResponseEntity<>(createdElection, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ElectionDto> updateElectionById(@Valid @PathVariable Long id, @RequestBody ElectionDto electionDto) {
        electionDto.setId(id);
        ElectionDto updatedElection = electionService.updateElection(electionDto);
        return ResponseEntity.ok(updatedElection);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteElectionById(@PathVariable Long id) {
        electionService.deleteElection(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<Void> startElectionById(@Valid @PathVariable Long id) {
        electionService.startElection(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/end")
    public ResponseEntity<Void> endElectionById(@Valid @PathVariable Long id) {
        electionService.endElection(id);
        return ResponseEntity.ok().build();
    }
}
