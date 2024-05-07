package javau9.ca.evote.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class VoteDto {

    private Long id;
    private Long userId;
    private Long voteOptionId;
    private Long electionId;
    @NotNull
    private LocalDateTime timestamp;

    public VoteDto() {}

    public VoteDto(Long userId, Long voteOptionId, Long electionId,LocalDateTime timestamp) {
        this.userId = userId;
        this.voteOptionId = voteOptionId;
        this.electionId = electionId;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVoteOptionId() {
        return voteOptionId;
    }

    public void setVoteOptionId(Long voteOptionId) {
        this.voteOptionId = voteOptionId;
    }

    public Long getElectionId() {
        return electionId;
    }

    public void setElectionId(Long electionId) {
        this.electionId = electionId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
