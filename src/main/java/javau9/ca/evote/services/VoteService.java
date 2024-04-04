package javau9.ca.evote.services;

import javau9.ca.evote.dto.VoteDto;

import java.util.List;
import java.util.Map;

public interface VoteService {

    VoteDto castVote(VoteDto voteDto) throws Exception;

    List<VoteDto> findAllVotesByElectionId(Long electionId);

    Map<Long, Integer> countVotesByElectionId(Long electionId);

    List<VoteDto> findAllVotesByUserId(Long userId);


}
