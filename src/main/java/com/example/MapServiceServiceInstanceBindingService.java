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
		String spaceId = createServiceInstanceBindingRequest.getServiceInstanceId();
		String userId = createServiceInstanceBindingRequest.getBindingId();
		MapServerUser user = mapServerClient.createUser(spaceId, userId);
		Map<String, Object> credentials = new LinkedHashMap<>();
		credentials.put("url", mapServiceUri + "/spaces/" + spaceId + "/map");
		credentials.put("userId", user.getUserId());
		credentials.put("password", user.getPassword());
		return new CreateServiceInstanceAppBindingResponse().withCredentials(credentials);
	}

	@Override
	public void deleteServiceInstanceBinding(
			DeleteServiceInstanceBindingRequest deleteServiceInstanceBindingRequest) {
		String spaceId = deleteServiceInstanceBindingRequest.getServiceInstanceId();
		String userId = deleteServiceInstanceBindingRequest.getBindingId();
		mapServerClient.deleteUser(spaceId, userId);
	}
}
