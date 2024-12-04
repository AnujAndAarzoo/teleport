package com.teleport.logistics;

import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.logistics.controller.LogisticsController;
import com.teleport.logistics.dto.ItemsTrackingResponse;
import com.teleport.logistics.util.Constants;

@SpringBootTest
@AutoConfigureMockMvc
class TeleportLogisticsServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	LogisticsController logisticsController;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testGenerateTrackingNumber() {

		String originCountryId = "MY";
		String destinationCountryId = "ID";
		String weight = "1.234";
		String createdAt = "2018-11-20T19:29:32+08:00";
		String customerId = "de619854-b59b-425e-9db4-943979e1bd49";
		String customerName = "RedBox Logistics";
		String customerSlug = "redbox-logistics";

		ItemsTrackingResponse expectedResponse = new ItemsTrackingResponse();
		expectedResponse.setResponse_code(Constants.SUCCESS_RESP_CODE);
		expectedResponse.setResponse_msg(Constants.SUCCESS);

		try {
			String expectedJson = objectMapper.writeValueAsString(expectedResponse);

			mockMvc.perform(get("/next-tracking-number").param("origin_country_id", originCountryId)
					.param("destination_country_id", destinationCountryId).param("weight", weight)
					.param("created_at", createdAt).param("customer_id", customerId)
					.param("customer_name", customerName).param("customer_slug", customerSlug))
					// Expect HTTP 200 OK
					.andExpect(status().isOk())
					// Validate response matches the required regex
					// .andExpect(content().string(matchesPattern("^[A-Z0-9]{1,16}$")));
					//.andExpect(content().json(expectedJson));
					.andExpect(content().json("{\"response_code\":\"00\"}"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
