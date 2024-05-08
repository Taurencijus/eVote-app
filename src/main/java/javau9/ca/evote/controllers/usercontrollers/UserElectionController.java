package javau9.ca.evote.controllers.usercontrollers;

import javau9.ca.evote.dto.ElectionDto;
import javau9.ca.evote.models.User;
import javau9.ca.evote.services.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<List<ElectionDto>> getAllElections(Authentication authentication) {
        User userDetails = (User) authentication.getPrincipal();
        Long userId = userDetails.getId();
        List<ElectionDto> elections = electionService.findAllElections(userId);
        return ResponseEntity.ok(elections);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElectionDto> getElectionById(@PathVariable Long id) {
        ElectionDto electionDto = electionService.findElectionById(id);
        return ResponseEntity.ok(electionDto);
    }
}
