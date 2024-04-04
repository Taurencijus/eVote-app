package javau9.ca.evote.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ElectionDto {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Long> voteOptionIds;

    public ElectionDto() {}

    public ElectionDto(Long id, String title, String description, LocalDateTime startTime, LocalDateTime endTime, List<Long> voteOptionIds) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.voteOptionIds = voteOptionIds;
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
}
