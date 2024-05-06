package javau9.ca.evote.controllers.usercontrollers;

import javau9.ca.evote.dto.ElectionDto;
import javau9.ca.evote.services.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/elections")
public class UserElectionController {

    ElectionService electionService;

    @Autowired
    public UserElectionController(ElectionService electionService) {
        this.electionService = electionService;
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
}
