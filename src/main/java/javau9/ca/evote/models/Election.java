package javau9.ca.evote.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Election {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "Description is mandatory")
    private String description;
    @NotNull(message = "Start time is mandatory")
    private LocalDateTime startTime;
    @NotNull(message = "End time is mandatory")
    private LocalDateTime endTime;
    @OneToMany(mappedBy = "election", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<VoteOption> voteOptions = new HashSet<>();

    public Election() {}

    public Election(String title, String description, LocalDateTime startTime, LocalDateTime endTime, Set<VoteOption> voteOptions) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.voteOptions = voteOptions;
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

    public Set<VoteOption> getVoteOptions() {
        return voteOptions;
    }

    public void setVoteOptions(Set<VoteOption> voteOptions) {
        this.voteOptions = voteOptions;
    }

    public void addVoteOption(VoteOption voteOption) {
        voteOptions.add(voteOption);
        voteOption.setElection(this);
    }

    public void removeVoteOption(VoteOption voteOption) {
        voteOptions.remove(voteOption);
        voteOption.setElection(null);
    }
}