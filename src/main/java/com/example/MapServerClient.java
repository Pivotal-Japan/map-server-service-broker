package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@Component
public class MapServerClient {

	@Autowired
	RestTemplate restTemplate;
	@Value("${map-service.uri:http://localhost:8080}")
	URI mapServiceUri;

	public void createSpace(String spaceId) {
		restTemplate
				.exchange(
						RequestEntity
								.post(UriComponentsBuilder.fromUri(mapServiceUri)
										.pathSegment("spaces").build().toUri())
								.body(Collections.singletonMap("spaceId", spaceId)),
						Void.class);
	}

	public void deleteSpace(String spaceId) {
		restTemplate.exchange(
				RequestEntity.delete(UriComponentsBuilder.fromUri(mapServiceUri)
						.pathSegment("spaces", spaceId).build().toUri()).build(),
				Void.class);
	}

	public MapServerUser createUser(String spaceId, String userId) {
		JsonNode res = restTemplate
				.exchange(
						RequestEntity
								.post(UriComponentsBuilder.fromUri(mapServiceUri)
										.pathSegment("spaces", spaceId, "users").build()
										.toUri())
								.body(Collections.singletonMap("userId", userId)),
						JsonNode.class)
				.getBody();
		return new MapServerUser(res.get("userId").asText(),
				res.get("password").asText());
	}

	public void deleteUser(String spaceId, String userId) {
		restTemplate.exchange(RequestEntity
				.delete(UriComponentsBuilder.fromUri(mapServiceUri)
						.pathSegment("spaces", spaceId, "users", userId).build().toUri())
				.build(), Void.class);
	}
}
