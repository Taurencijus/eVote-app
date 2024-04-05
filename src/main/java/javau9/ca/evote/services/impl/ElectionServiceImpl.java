package javau9.ca.evote.services.impl;

import javau9.ca.evote.dto.ElectionDto;
import javau9.ca.evote.exceptions.EntityNotFoundException;
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
        Election election = electionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Election not found with ID: " + id));
        return MapperUtils.convertToElectionDto(election);
    }

    @Override
    public ElectionDto updateElection(ElectionDto electionDto) {
        Election existingElection = electionRepository.findById(electionDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Election not found with ID: " + electionDto.getId()));
        existingElection.setTitle(electionDto.getTitle());
        existingElection.setDescription(electionDto.getDescription());
        existingElection.setStartTime(electionDto.getStartTime());
        existingElection.setEndTime(electionDto.getEndTime());

        Election updatedElection = electionRepository.save(existingElection);
        return MapperUtils.convertToElectionDto(updatedElection);
    }

    @Override
    public void deleteElection(Long id) {
        electionRepository.deleteById(id);
    }

    @Override
    public void startElection(Long id) {
        Election election = electionRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Election not found with ID: " + id));

        if (election.getStartTime() != null) {
            throw new IllegalStateException("Election has already been started.");
        }
        if (election.getEndTime() != null && election.getEndTime().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Election has already ended.");
        }

        election.setStartTime(LocalDateTime.now());
        electionRepository.save(election);
    }

    @Override
    public void endElection(Long id) {
        Election election = electionRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Election not found with ID: " + id));

        if (election.getStartTime() == null) {
            throw new IllegalStateException("Election has not started yet.");
        }
        if (election.getEndTime() != null) {
            throw new IllegalStateException("Election has already been ended.");
        }
        if (election.getStartTime().isAfter(LocalDateTime.now())) {
            throw new IllegalStateException("Election start time is in the future.");
        }

        election.setEndTime(LocalDateTime.now());
        electionRepository.save(election);
    }
}
