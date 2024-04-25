package javau9.ca.evote.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class VoteOptionDto {

    private Long id;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 50, message = "Name has to be between 3 and 50 characters")
    private String name;
    @NotBlank(message = "Description is mandatory")
    @Size(min = 3, max = 500, message = "Description has to be between 3 and 500 characters")
    private String description;
    private Long electionId;

    public VoteOptionDto() {}

    public VoteOptionDto(Long id, String name, String description, Long electionId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.electionId = electionId;
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

    public Long getElectionId() {
        return electionId;
    }

    public void setElectionId(Long electionId) {
        this.electionId = electionId;
    }
}
