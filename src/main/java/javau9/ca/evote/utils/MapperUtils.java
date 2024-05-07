package javau9.ca.evote.utils;

import javau9.ca.evote.dto.ElectionDto;
import javau9.ca.evote.dto.UserDto;
import javau9.ca.evote.dto.VoteDto;
import javau9.ca.evote.dto.VoteOptionDto;
import javau9.ca.evote.models.Election;
import javau9.ca.evote.models.User;
import javau9.ca.evote.models.Vote;
import javau9.ca.evote.models.VoteOption;
import javau9.ca.evote.repositories.VoteRepository;

import java.util.stream.Collectors;

public class MapperUtils {

    public static ElectionDto convertToElectionDto(Election election) {
        if (election == null) {
            return null;
        }
        ElectionDto dto = new ElectionDto();
        dto.setId(election.getId());
        dto.setTitle(election.getTitle());
        dto.setDescription(election.getDescription());
        dto.setStartTime(election.getStartTime());
        dto.setEndTime(election.getEndTime());

        if (election.getVoteOptions() != null) {
            dto.setVoteOptionIds(election.getVoteOptions().stream()
                    .map(VoteOption::getId)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public static Election convertToElectionEntity(ElectionDto dto) {
        if (dto == null) {
            return null;
        }
        Election election = new Election();
        election.setId(dto.getId());
        election.setTitle(dto.getTitle());
        election.setDescription(dto.getDescription());
        election.setStartTime(dto.getStartTime());
        election.setEndTime(dto.getEndTime());

        return election;
    }

    public static ElectionDto convertToElectionDtoWithVotingStatus(Election election, Long userId, VoteRepository voteRepository) {
        ElectionDto dto = convertToElectionDto(election);
        if (dto != null && userId != null) {
            boolean hasVoted = voteRepository.existsByUserIdAndElectionId(userId, election.getId());
            dto.setHasVoted(hasVoted);
        }
        return dto;
    }

    public static UserDto convertToUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setType(user.getType());

        return dto;
    }

    public static User convertToUserEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        //user.setPassword(dto.getPassword()); // Need to ensure this is handled securely, e.g., encoded
        user.setType(dto.getType());

        return user;
    }

    public static VoteOptionDto convertToVoteOptionDto(VoteOption voteOption) {
        if (voteOption == null) {
            return null;
        }
        VoteOptionDto dto = new VoteOptionDto();
        dto.setId(voteOption.getId());
        dto.setName(voteOption.getName());
        dto.setDescription(voteOption.getDescription());
        if (voteOption.getElection() != null) {
            dto.setElectionId(voteOption.getElection().getId());
        }
        return dto;
    }

    public static VoteOption convertToVoteOptionEntity(VoteOptionDto dto) {
        if (dto == null) {
            return null;
        }
        VoteOption voteOption = new VoteOption();
        voteOption.setId(dto.getId()); // Need to be cautious: setting ID is typically for updates
        voteOption.setName(dto.getName());
        voteOption.setDescription(dto.getDescription());

        return voteOption;
    }

    public static VoteDto convertToVoteDto(Vote vote) {
        if (vote == null) {
            return null;
        }
        VoteDto dto = new VoteDto();
        dto.setId(vote.getId());
        dto.setUserId(vote.getUser() != null ? vote.getUser().getId() : null);
        dto.setVoteOptionId(vote.getVoteOption() != null ? vote.getVoteOption().getId() : null);
        dto.setTimestamp(vote.getTimestamp());
        return dto;
    }

    public static Vote convertToVoteEntity(VoteDto dto) {
        if (dto == null) {
            return null;
        }
        Vote vote = new Vote();
        vote.setTimestamp(dto.getTimestamp());
        return vote;
    }
}
