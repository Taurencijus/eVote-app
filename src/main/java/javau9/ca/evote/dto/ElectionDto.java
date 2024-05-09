package javau9.ca.evote.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public class ElectionDto {

    private Long id;
    @NotBlank(message = "Title is mandatory")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    private String title;
    @NotBlank(message = "Description is mandatory")
    @Size(min = 3, max = 500, message = "Description mus be between 3 and 500 characters")
    private String description;
    @NotBlank(message = "Start time is mandatory")
    private LocalDateTime startTime;
    @NotBlank(message = "End time is mandatory")
    private LocalDateTime endTime;
    private List<Long> voteOptionIds;

    private List<VoteOptionDto> voteOptionDtos;

    private boolean hasVoted;

    public ElectionDto() {}

    public ElectionDto(Long id, String title, String description, LocalDateTime startTime, LocalDateTime endTime, List<Long> voteOptionIds, boolean hasVoted, List<VoteOptionDto> voteOptionDtos) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.voteOptionIds = voteOptionIds;
        this.hasVoted = hasVoted;
        this.voteOptionDtos = voteOptionDtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<Long> getVoteOptionIds() {
        return voteOptionIds;
    }

    public void setVoteOptionIds(List<Long> voteOptionIds) {
        this.voteOptionIds = voteOptionIds;
    }

    public boolean getHasVoted() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

    public List<VoteOptionDto> getVoteOptionDtos() {
        return voteOptionDtos;
    }

    public void setVoteOptionDtos(List<VoteOptionDto> voteOptionDtos) {
        this.voteOptionDtos = voteOptionDtos;
    }
}
