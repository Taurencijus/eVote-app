package javau9.ca.evote.services;

import javau9.ca.evote.dto.ElectionDto;

import java.util.List;

public interface ElectionService {

    ElectionDto createElection(ElectionDto election);

    List<ElectionDto> findAllElections();

    ElectionDto findElectionById(Long id);

    ElectionDto updateElection(ElectionDto election);

    void deleteElection(Long id);

    void startElection(Long id);

    void endElection (Long id);
}
