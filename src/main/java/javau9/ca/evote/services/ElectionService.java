package javau9.ca.evote.services;

import javau9.ca.evote.dto.ElectionDto;

import java.util.List;

public interface ElectionService {

    ElectionDto createElection(ElectionDto electionDto);
    List<ElectionDto> findAllElections(Long userId);
    ElectionDto findElectionById(Long id);
    ElectionDto updateElection(ElectionDto electionDto);
    void deleteElection(Long id);
    void startElection(Long id);
    void endElection (Long id);
}
