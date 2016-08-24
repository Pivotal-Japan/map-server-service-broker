package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceAppBindingResponse;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingResponse;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.service.ServiceInstanceBindingService;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class MapServiceServiceInstanceBindingService
		implements ServiceInstanceBindingService {
	@Autowired
	MapServerClient mapServerClient;
	@Value("${map-service.uri:http://localhost:8080}")
	String mapServiceUri;

	@Override
	public CreateServiceInstanceBindingResponse createServiceInstanceBinding(
			CreateServiceInstanceBindingRequest createServiceInstanceBindingRequest) {
		// TODO-03: Create User
		Map<String, Object> credentials = new LinkedHashMap<>();
		credentials.put("url", null /* TODO-04: URL to Map API for the created user */);
		credentials.put("userId", null /* TODO-05: userId for the created user */);
		credentials.put("password", null /* TODO-06: password for the created user */);
		return new CreateServiceInstanceAppBindingResponse().withCredentials(credentials);
	}

	@Override
	public void deleteServiceInstanceBinding(
			DeleteServiceInstanceBindingRequest deleteServiceInstanceBindingRequest) {
		// TODO-07: Delete User
	}
}
