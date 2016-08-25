package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.servicebroker.model.Catalog;
import org.springframework.cloud.servicebroker.model.Plan;
import org.springframework.cloud.servicebroker.model.ServiceDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static java.util.Collections.*;

@SpringBootApplication
public class MapServerServiceBrokerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapServerServiceBrokerApplication.class, args);
	}

	@Bean
	Catalog catalog(MapServerServiceBrokerConfigProperties properties) {
		return new Catalog(singletonList(new ServiceDefinition(
				properties.getServiceDefinitionId(), properties.getServiceDefinitionId(),
				"A map-server service broker", true, false,
				singletonList(new Plan(properties.getPlanId(), "free", "free plan",
						new HashMap<String, Object>() {
							{
								put("costs", singletonList(new HashMap<String, Object>() {
									{
										put("amount", singletonMap("usd", 0.0));
										put("unit", "MONTHLY");
									}
								}));
								put("bullets", Arrays.asList("simple hash-map server",
										"for demo"));
							}
						}, true)),
				Arrays.asList("map", "kvs"), new HashMap<String, Object>() {
					{
						put("displayName", "Map Server");
						put("longDescription", "Map Server Service Broker");
						put("imageUrl",
								"https://www.cloudfoundry.org/wp-content/uploads/2015/10/CF_rabbit_Blacksmith_rgb_trans_back-269x300.png");
						put("providerDisplayName", "@making");
					}
				}, null, null)));
	}

	@Bean
	RestTemplate restTemplate(MapServerServiceBrokerConfigProperties properties) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate
				.setInterceptors(Collections.singletonList((request, body, execution) -> {
					String token = Base64.getEncoder()
							.encodeToString((properties.getAdmin().getUsername() + ":"
									+ properties.getAdmin().getPassword()).getBytes());
					request.getHeaders().add("Authorization", "Basic " + token);
					return execution.execute(request, body);
				}));
		return restTemplate;
	}
}
