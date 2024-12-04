package com.teleport.logistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teleport.logistics.dto.ItemsTrackingResponse;
import com.teleport.logistics.service.LogisticsApplicationService;

@RestController
public class LogisticsController {

	@Autowired
	private LogisticsApplicationService logisticsApplicationService;

	@GetMapping("/next-tracking-number")
	public ResponseEntity<ItemsTrackingResponse> getNextTrackingNumber(
			@RequestParam("origin_country_id") String originCountryId,
			@RequestParam("destination_country_id") String destinationCountryId, @RequestParam("weight") String weight,
			@RequestParam("created_at") String createdAt, @RequestParam("customer_id") String customerId,
			@RequestParam("customer_name") String customerName, @RequestParam("customer_slug") String customerSlug) {

		String data = originCountryId + destinationCountryId + weight + createdAt + customerId + customerName
				+ customerSlug;

		ItemsTrackingResponse trackingResponse = logisticsApplicationService.generateTrackingNumber(data);

		return new ResponseEntity<>(trackingResponse, HttpStatus.OK);
	}
}
