package hr.tvz.knezevic.njamapp.controllers;

import hr.tvz.knezevic.njamapp.model.Restaurant;
import hr.tvz.knezevic.njamapp.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private Restaurant testRestaurant;

    @BeforeEach
    void setup() {
        restaurantRepository.deleteAll();

        testRestaurant = new Restaurant();
        testRestaurant.setName("Testaurant");
        testRestaurant.setAddress("Test Address 123");
        testRestaurant.setPhoneNumber("123-456-789");
        testRestaurant.setEmail("contact@testaurant.com");
        testRestaurant.setDescription("Test description");
        testRestaurant.setOpened(true);
        testRestaurant.setAverageDeliveryTime(30.0);
        testRestaurant.setAverageCustomerRating(4.5);
        testRestaurant.setMaxNumberOfOrders(10);
        testRestaurant.setMichelinStars(1);

        testRestaurant.setWorkingHours(Collections.emptyList());

        restaurantRepository.save(testRestaurant);
    }

    @Test
    @WithMockUser(username = "user123")
    void findAll_shouldReturnRestaurants() throws Exception {
        mockMvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Testaurant"))
                .andExpect(jsonPath("$[0].address").value("Test Address 123"));
    }

    @Test
    @WithMockUser(username = "user123")
    void findById_shouldReturnRestaurant() throws Exception {
        mockMvc.perform(get(String.format("/restaurants/%d", testRestaurant.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Testaurant"))
                .andExpect(jsonPath("$.phoneNumber").value("123-456-789"))
                .andExpect(jsonPath("$.email").value("contact@testaurant.com"));
    }

    @Test
    @WithMockUser(username = "user123")
    void findById_shouldReturnNotFoundForInvalidId() throws Exception {
        mockMvc.perform(get("/restaurants/999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "user123")
    void addRestaurant_shouldCreateNewRestaurant() throws Exception {
        String newRestaurantJson = """
            {
                "name": "New Restaurant",
                "address": "New Address 456",
                "phoneNumber": "0951834567",
                "email": "new@restaurant.com",
                "description": "Nice place",
                "opened": true,
                "averageDeliveryTime": 25.0,
                "averageCustomerRating": 4.7,
                "maxNumberOfOrders": 15,
                "michelinStars": 0,
                "workingHours": [
                    {
                        "dayOfWeek": "MONDAY",
                        "workingHour": "08:00-16:00"
                    }
                ]
            }
            """;

        mockMvc.perform(post("/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newRestaurantJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Restaurant"))
                .andExpect(jsonPath("$.address").value("New Address 456"));

        List<Restaurant> restaurants = restaurantRepository.findAll();
        assertEquals(2, restaurants.size());
    }
}