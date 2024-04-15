package javau9.ca.evote.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
public class VoteOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Description is mandatory")
    private String description;

    @OneToMany(mappedBy = "voteOption", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Vote> votes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "election_id", nullable = false)
    private Election election;

    public VoteOption() {}

    public VoteOption(String name, String description, Set<Vote> votes, Election election) {
        this.name = name;
        this.description = description;
        this.votes = votes;
        this.election = election;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public void addVote(Vote vote) {
        votes.add(vote);
        vote.setVoteOption(this);
    }

    public void removeVote(Vote vote) {
        votes.remove(vote);
        vote.setVoteOption(null);
    }
}
