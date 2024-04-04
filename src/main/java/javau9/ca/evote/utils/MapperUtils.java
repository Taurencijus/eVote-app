package javau9.ca.evote.utils;

import javau9.ca.evote.dto.ElectionDto;
import javau9.ca.evote.models.Election;
import javau9.ca.evote.models.VoteOption;

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




}
