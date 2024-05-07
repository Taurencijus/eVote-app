package javau9.ca.evote.services.impl;

import javau9.ca.evote.dto.VoteDto;
import javau9.ca.evote.exceptions.EntityNotFoundException;
import javau9.ca.evote.models.User;
import javau9.ca.evote.models.Vote;
import javau9.ca.evote.models.VoteOption;
import javau9.ca.evote.repositories.UserRepository;
import javau9.ca.evote.repositories.VoteOptionRepository;
import javau9.ca.evote.repositories.VoteRepository;
import javau9.ca.evote.services.VoteService;
import javau9.ca.evote.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VoteServiceImpl implements VoteService {


    VoteRepository voteRepository;
    UserRepository userRepository;
    VoteOptionRepository voteOptionRepository;
    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository,
                           UserRepository userRepository,
                           VoteOptionRepository voteOptionRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.voteOptionRepository = voteOptionRepository;
    }

    /*@Override
    public VoteDto castVote(VoteDto voteDto) {
        User user = userRepository.findById(voteDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + voteDto.getUserId()));
        VoteOption voteOption = voteOptionRepository.findById(voteDto.getVoteOptionId())
                .orElseThrow(() -> new EntityNotFoundException("Vote Option not found with ID: " + voteDto.getVoteOptionId()));
        Vote vote = new Vote();
        vote.setUser(user);
        vote.setVoteOption(voteOption);
        vote.setTimestamp(voteDto.getTimestamp() != null ? voteDto.getTimestamp() : LocalDateTime.now());
        vote = voteRepository.save(vote);

        return MapperUtils.convertToVoteDto(vote);
    }*/

    public VoteDto castVote(Long userId, Long voteOptionId, Long electionId) {
        if (voteRepository.existsByUserIdAndVoteOption_Election_Id(userId, electionId)) {
            throw new IllegalStateException("User has already voted in this election.");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        VoteOption voteOption = voteOptionRepository.findById(voteOptionId)
                .orElseThrow(() -> new EntityNotFoundException("Vote Option not found with ID: " + voteOptionId));

        if (!voteOption.getElection().getId().equals(electionId)) {
            throw new IllegalStateException("The vote option does not belong to this election.");
        }
        Vote vote = new Vote();
        vote.setUser(user);
        vote.setVoteOption(voteOption);
        vote.setElection(voteOption.getElection());
        vote.setTimestamp(LocalDateTime.now());
        vote = voteRepository.save(vote);

        return MapperUtils.convertToVoteDto(vote);
    }

    @Override
    public List<VoteDto> findAllVotesByElectionId(Long electionId) {
        List<Vote> votes = voteRepository.findByElectionId(electionId);
        return votes.stream()
                .map(MapperUtils::convertToVoteDto)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Long, Integer> countVotesByElectionId(Long electionId) {
        List<Object[]> voteCounts = voteRepository.countVotesByElectionId(electionId);
        return voteCounts.stream().collect(Collectors.toMap(
                voteCount -> (Long) voteCount[0],
                voteCount -> ((Number) voteCount[1]).intValue()
        ));
    }

    @Override
    public void deleteVote(Long voteId) {
        if (!voteRepository.existsById(voteId)) {
            throw new EntityNotFoundException("Vote not found with ID: " + voteId);
        }
        voteRepository.deleteById(voteId);
    }
}
