package javau9.ca.evote.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import javau9.ca.evote.dto.VoteDto;
import javau9.ca.evote.services.VoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(VoteController.class)
public class VoteControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VoteService voteService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void castVote_WhenPostMethod() throws Exception {
        VoteDto voteDto = new VoteDto();
        given(voteService.castVote(any(VoteDto.class))).willReturn(voteDto);

        mockMvc.perform(post("/api/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(voteDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void getAllVotesByElectionId_WhenGetMethod() throws Exception {
        Long electionId = 1L;
        List<VoteDto> votes = List.of(new VoteDto(), new VoteDto());
        given(voteService.findAllVotesByElectionId(electionId)).willReturn(votes);

        mockMvc.perform(get("/api/votes/election/{electionId}", electionId)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(votes.size())));
    }

    @Test
    public void countVotesByElectionId_WhenGetMethod() throws Exception {
        Long electionId = 1L;
        Map<Long, Integer> voteCounts = Map.of(1L, 10, 2L, 20);
        given(voteService.countVotesByElectionId(electionId)).willReturn(voteCounts);

        mockMvc.perform(get("/api/votes/count/election/{electionId}", electionId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", aMapWithSize(voteCounts.size())));
    }

    @Test
    public void deleteVote_WhenDeleteMethod() throws Exception {
        Long voteId = 1L;
        mockMvc.perform(delete("/api/votes/{voteId}", voteId))
                .andExpect(status().isNoContent());
    }
}
