package javau9.ca.evote.services;

import javau9.ca.evote.dto.VoteOptionDto;

import java.util.List;

public interface VoteOptionService {

    VoteOptionDto createVoteOption(VoteOptionDto voteOptionDto);

    List<VoteOptionDto> findAllVoteOptions();

    VoteOptionDto findVoteOptionById(Long id);

    VoteOptionDto updateVoteOption(VoteOptionDto voteOptionDto);

    void deleteVoteOption(Long id);

    List<VoteOptionDto> findVoteOptionsByElectionId(Long electionId);
}
