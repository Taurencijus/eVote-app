package javau9.ca.evote.services.impl;

import javau9.ca.evote.dto.ElectionDto;
import javau9.ca.evote.models.Election;
import javau9.ca.evote.repositories.ElectionRepository;
import javau9.ca.evote.services.ElectionService;
import javau9.ca.evote.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ElectionServiceImpl implements ElectionService {


    ElectionRepository electionRepository;

    @Autowired
    public ElectionServiceImpl(ElectionRepository electionRepository) {
        this.electionRepository = electionRepository;
    }

    @Override
    public ElectionDto createElection(ElectionDto electionDto) {
        Election election = MapperUtils.convertToElectionEntity(electionDto);
        election = electionRepository.save(election);
        return MapperUtils.convertToElectionDto(election);
    }

    @Override
    public List<ElectionDto> findAllElections() {
        return electionRepository.findAll().stream()
                .map(MapperUtils::convertToElectionDto)
                .collect(Collectors.toList());
    }

    @Override
    public ElectionDto findElectionById(Long id) {
        return electionRepository.findById(id)
                .map(MapperUtils::convertToElectionDto)
                .orElse(null);
    }


    @Override
    public void deleteElection(Long id) {
        electionRepository.deleteById(id);
    }

    @Override
    public void startElection(Long id) {
        Election election = electionRepository
                .findById(id).orElseThrow(() -> new RuntimeException("Election not found with ID: " + id));
        election.setStartTime(LocalDateTime.now());
        electionRepository.save(election);
    }

    @Override
    public void endElection(Long id) {
        Election election = electionRepository
                .findById(id).orElseThrow(() -> new RuntimeException("Election not found with ID: " + id));
    }
}
