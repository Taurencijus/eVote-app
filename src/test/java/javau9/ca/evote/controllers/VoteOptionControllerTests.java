package javau9.ca.evote.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import javau9.ca.evote.dto.VoteOptionDto;
import javau9.ca.evote.services.VoteOptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VoteOptionController.class)
public class VoteOptionControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VoteOptionService voteOptionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createVoteOption_whenPostMethod() throws Exception {
        VoteOptionDto voteOptionDto = new VoteOptionDto();
        given(voteOptionService.createVoteOption(any(VoteOptionDto.class))).willReturn(voteOptionDto);

        mockMvc.perform(post("/api/vote-options")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(voteOptionDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void getAllVoteOptions_whenGetMethod() throws Exception {
        List<VoteOptionDto> voteOptions = List.of(new VoteOptionDto(), new VoteOptionDto());
        given(voteOptionService.findAllVoteOptions()).willReturn(voteOptions);

        mockMvc.perform(get("/api/vote-options")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(voteOptions.size())));
    }

    @Test
    public void getVoteOptionsByElectionId_whenGetMethod() throws Exception {
        Long electionId = 1L;
        List<VoteOptionDto> voteOptions = List.of(new VoteOptionDto(), new VoteOptionDto());
        given(voteOptionService.findVoteOptionsByElectionId(electionId)).willReturn(voteOptions);

        mockMvc.perform(get("/api/vote-options/election/{electionId}", electionId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(voteOptions.size())));
    }

    @Test
    public void updateVoteOption_whenPutMethod() throws Exception {
        Long id = 1L;
        VoteOptionDto voteOptionDto = new VoteOptionDto();
        voteOptionDto.setId(id);
        given(voteOptionService.updateVoteOption(any(VoteOptionDto.class))).willAnswer(invocation -> invocation.getArgument(0));

        mockMvc.perform(put("/api/vote-options/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(voteOptionDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id.intValue())));
    }

    @Test
    public void deleteVoteOption_whenDeleteMethod() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/vote-options/{id}", id))
                .andExpect(status().isNoContent());
    }
}
