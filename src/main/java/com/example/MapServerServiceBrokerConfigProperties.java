package com.example;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@ConfigurationProperties(prefix = "map-service")
@Component
public class MapServerServiceBrokerConfigProperties {
	@Valid
	private Admin admin;
	@URL
	@NotEmpty
	private String uri;
	@NotEmpty
	private String planId;
	@NotEmpty
	private String serviceDefinitionId;
	@NotEmpty
	private String serviceDefinitionName;

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getServiceDefinitionId() {
		return serviceDefinitionId;
	}

	public void setServiceDefinitionId(String serviceDefinitionId) {
		this.serviceDefinitionId = serviceDefinitionId;
	}

	public String getServiceDefinitionName() {
		return serviceDefinitionName;
	}

	public void setServiceDefinitionName(String serviceDefinitionName) {
		this.serviceDefinitionName = serviceDefinitionName;
	}

	public static class Admin {
		@NotEmpty
		private String username;
		@NotEmpty
		private String password;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
}
