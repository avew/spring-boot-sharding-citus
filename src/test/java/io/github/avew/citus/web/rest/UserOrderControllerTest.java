package io.github.avew.citus.web.rest;

import io.github.avew.citus.domain.UserOrder;
import io.github.avew.citus.repository.UserOrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserOrderRepository userOrderRepository;

    @AfterEach
    public void cleanup() {
        userOrderRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testCreateUserOrder() throws Exception {
        // Test data
        String userOrderJson = "{\"userId\":1,\"orderAmount\":100.50}";

        // Perform POST request and verify response
        mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userOrderJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.orderAmount", is(100.50)));
    }

    @Test
    @Transactional
    public void testGetAllUserOrders() throws Exception {
        // Create test data
        UserOrder order1 = UserOrder.builder()
                .userId(1L)
                .orderAmount(new BigDecimal("150.75"))
                .build();

        UserOrder order2 = UserOrder.builder()
                .userId(2L)
                .orderAmount(new BigDecimal("200.25"))
                .build();

        userOrderRepository.save(order1);
        userOrderRepository.save(order2);

        // Perform GET request and verify response
        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[1].userId", is(2)));
    }
}
