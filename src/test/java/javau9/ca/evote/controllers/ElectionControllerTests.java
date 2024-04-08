package javau9.ca.evote.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import javau9.ca.evote.dto.ElectionDto;
import javau9.ca.evote.services.ElectionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(ElectionController.class)
public class ElectionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ElectionService electionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createElection_whenPostMethod() throws Exception {
        ElectionDto electionDto = new ElectionDto();
        electionDto.setTitle("Test Election");
        electionDto.setDescription("This is a test election.");

        given(electionService.createElection(electionDto)).willReturn(electionDto);

        mockMvc.perform(post("/api/elections")
                            .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(electionDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void getAllElections_whenGetMethod() throws Exception {
        List<ElectionDto> elections = Arrays.asList(new ElectionDto(), new ElectionDto());
        given(electionService.findAllElections()).willReturn(elections);

        mockMvc.perform(get("/api/elections")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(elections.size())));
    }

    @Test
    public void getElectionById_whenGetMethod() throws Exception {
        Long id = 1L;
        ElectionDto electionDto = new ElectionDto();
        electionDto.setId(id);
        given(electionService.findElectionById(id)).willReturn(electionDto);

        mockMvc.perform(get("/api/elections/{id}", id)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id.intValue())));
    }

    @Test
    public void updateElection_whenPostMethod() throws Exception {
        Long id = 1L;
        ElectionDto electionDto = new ElectionDto();
        electionDto.setTitle("Update Election");
        given(electionService.updateElection(any(ElectionDto.class)))
                .willAnswer(invocation -> invocation.getArgument(0));

        mockMvc.perform(post("/api/elections/{id}", id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(electionDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(electionDto.getTitle())));
    }

    @Test
    public void deleteElection_whenDeleteMethod() throws Exception {
        Long id = 1L;

        doNothing().when(electionService).deleteElection(id);

        mockMvc.perform(delete("/api/elections/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    public void startElection_whenPostMethod() throws Exception {
        Long id = 1L;

        doNothing().when(electionService).startElection(id);

        mockMvc.perform(post("/api/elections/{id}/start", id))
                .andExpect(status().isOk());
    }

    @Test
    public void endElection_whenPostMethod() throws Exception {
        Long id = 1L;

        doNothing().when(electionService).endElection(id);

        mockMvc.perform(post("/api/elections/{id}/end", id))
                .andExpect(status().isOk());
    }



}
