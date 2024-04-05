package javau9.ca.evote.services.impl;

import javau9.ca.evote.dto.VoteOptionDto;
import javau9.ca.evote.exceptions.EntityNotFoundException;
import javau9.ca.evote.models.Election;
import javau9.ca.evote.models.VoteOption;
import javau9.ca.evote.repositories.ElectionRepository;
import javau9.ca.evote.repositories.VoteOptionRepository;
import javau9.ca.evote.services.VoteOptionService;
import javau9.ca.evote.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoteOptionServiceImpl implements VoteOptionService {

    VoteOptionRepository voteOptionRepository;
    ElectionRepository electionRepository;

    @Autowired
    public VoteOptionServiceImpl(VoteOptionRepository voteOptionRepository, ElectionRepository electionRepository) {
        this.voteOptionRepository = voteOptionRepository;
        this.electionRepository = electionRepository;
    }

    @Override
    public VoteOptionDto createVoteOption(VoteOptionDto voteOptionDto) {
        VoteOption voteOption = MapperUtils.convertToVoteOptionEntity(voteOptionDto);
        Election election = electionRepository.findById(voteOptionDto.getElectionId())
                .orElseThrow(() -> new RuntimeException("Election not found with ID: " + voteOptionDto.getElectionId()));
        voteOption.setElection(election);
        VoteOption savedVoteOption = voteOptionRepository.save(voteOption);
        return MapperUtils.convertToVoteOptionDto(savedVoteOption);
    }

    @Override
    public List<VoteOptionDto> findAllVoteOptions() {
        return voteOptionRepository.findAll().stream()
                .map(MapperUtils::convertToVoteOptionDto)
                .collect(Collectors.toList());
    }

    @Override
    public VoteOptionDto findVoteOptionById(Long id) {
        return voteOptionRepository.findById(id)
                .map(MapperUtils::convertToVoteOptionDto)
                .orElse(null);
    }

    @Override
    public List<VoteOptionDto> findVoteOptionsByElectionId(Long electionId) {
        List<VoteOption> voteOptions = voteOptionRepository.findByElectionId(electionId);
        return voteOptions.stream()
                .map(MapperUtils::convertToVoteOptionDto)
                .collect(Collectors.toList());
    }


    @Override
    public VoteOptionDto updateVoteOption(VoteOptionDto voteOptionDto) {
        VoteOption existingVoteOption = voteOptionRepository.findById(voteOptionDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("VoteOption not found with ID: " + voteOptionDto.getId()));
        existingVoteOption.setName(voteOptionDto.getName());
        existingVoteOption.setDescription(voteOptionDto.getDescription());
        VoteOption updatedVoteOption = voteOptionRepository.save(existingVoteOption);
        return MapperUtils.convertToVoteOptionDto(updatedVoteOption);
    }

    @Override
    public void deleteVoteOption(Long id) {
        voteOptionRepository.deleteById(id);
    }
}
