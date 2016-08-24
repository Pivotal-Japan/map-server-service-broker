package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.servicebroker.model.*;
import org.springframework.cloud.servicebroker.service.ServiceInstanceService;
import org.springframework.stereotype.Component;

@Component
public class MapServerServiceInstanceService implements ServiceInstanceService {

    @Autowired
    MapServerClient mapServerClient;

    @Override
    public CreateServiceInstanceResponse createServiceInstance(
            CreateServiceInstanceRequest createServiceInstanceRequest) {
        // TODO-01: Create Space
        return new CreateServiceInstanceResponse();
    }

    @Override
    public GetLastServiceOperationResponse getLastOperation(
            GetLastServiceOperationRequest getLastServiceOperationRequest) {
        return new GetLastServiceOperationResponse();
    }

    @Override
    public DeleteServiceInstanceResponse deleteServiceInstance(
            DeleteServiceInstanceRequest deleteServiceInstanceRequest) {
        // TODO-02: Delete Space
        return new DeleteServiceInstanceResponse();
    }

    @Override
    public UpdateServiceInstanceResponse updateServiceInstance(
            UpdateServiceInstanceRequest updateServiceInstanceRequest) {
        return new UpdateServiceInstanceResponse();
    }
}
