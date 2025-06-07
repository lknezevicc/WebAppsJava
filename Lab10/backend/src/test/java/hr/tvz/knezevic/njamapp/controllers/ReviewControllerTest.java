package hr.tvz.knezevic.njamapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.knezevic.njamapp.command.ReviewCommand;
import hr.tvz.knezevic.njamapp.dto.ReviewDTO;
import hr.tvz.knezevic.njamapp.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReviewService reviewService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @WithMockUser(username = "user123")
    void findAll_shouldReturnListOfReviews() throws Exception {
        ReviewDTO review = new ReviewDTO(1L, "Great!", "Really nice food", 5, "user123");
        when(reviewService.findAll()).thenReturn(List.of(review));

        mockMvc.perform(get("/reviews"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Great!"))
                .andExpect(jsonPath("$[0].rating").value(5));
    }

    @Test
    @WithMockUser(username = "user123")
    void findAllByRestaurantId_shouldReturnListOfReviews() throws Exception {
        ReviewDTO review = new ReviewDTO(1L, "Nice place", "Good service", 4, "user123");
        when(reviewService.findAllByRestaurantId(1L)).thenReturn(List.of(review));

        mockMvc.perform(get("/reviews/restaurant/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].description").value("Good service"))
                .andExpect(jsonPath("$[0].rating").value(4));
    }

    @Test
    @WithMockUser(roles = "USER")
    void addReview_shouldCreateReview_whenValid() throws Exception {
        ReviewCommand command = new ReviewCommand("Nice", "Good food", 5, 1L);
        ReviewDTO responseDto = new ReviewDTO(1L, "Nice", "Good food", 5, "user123");

        when(reviewService.addReview(any(ReviewCommand.class))).thenReturn(Optional.of(responseDto));

        mockMvc.perform(post("/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Nice"))
                .andExpect(jsonPath("$.rating").value(5));
    }

    @Test
    @WithMockUser(roles = "USER")
    void addReview_shouldReturnBadRequest_whenServiceReturnsEmpty() throws Exception {
        ReviewCommand command = new ReviewCommand("Bad", "No good", 1, 1L);

        when(reviewService.addReview(any(ReviewCommand.class))).thenReturn(Optional.empty());

        mockMvc.perform(post("/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isBadRequest());
    }
}