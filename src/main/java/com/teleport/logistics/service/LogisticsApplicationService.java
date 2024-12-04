package com.teleport.logistics.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.teleport.logistics.dto.ItemsTrackingResponse;
import com.teleport.logistics.exceptions.LogisticsAppException;
import com.teleport.logistics.util.Constants;
import com.teleport.logistics.util.LogisticsDateUtil;

@Service
public class LogisticsApplicationService {

	public ItemsTrackingResponse generateTrackingNumber(String data) {
		ItemsTrackingResponse trackingResponse = new ItemsTrackingResponse();

		try {
			// Use SHA-256 hash
			MessageDigest digest = MessageDigest.getInstance(Constants.SHA_256);
			byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));

			// Convert hash bytes to uppercase alphanumeric string
			StringBuilder hexString = new StringBuilder();
			for (byte b : hash) {
				String hex = Integer.toHexString(0xff & b).toUpperCase();
				hexString.append(hex);
			}

			// Limit to 16 characters and remove non-alphanumeric characters
			String trackingNumber = hexString.toString().replaceAll(Constants.REGEX_ALPHA_NUMBER, "").substring(0, 16);

			if (trackingNumber.matches(Constants.TRACKING_NUMBER_REGEX)) {
				trackingResponse.setTracking_number(trackingNumber);
				trackingResponse.setCreated_at(LogisticsDateUtil.generateCurrentDateInFormat());
				trackingResponse.setResponse_code(Constants.SUCCESS_RESP_CODE);
				trackingResponse.setResponse_msg(Constants.SUCCESS);
			} else {
				trackingResponse.setResponse_code(Constants.FAILURE_RESP_CODE);
				trackingResponse.setResponse_msg(Constants.FAILED);
			}
		} catch (NoSuchAlgorithmException e) {
			trackingResponse.setResponse_code(Constants.FAILURE_RESP_CODE);
			trackingResponse.setResponse_msg(Constants.INTERNAL_SERVER_ERROR);
		}
		return trackingResponse;
	}

}
