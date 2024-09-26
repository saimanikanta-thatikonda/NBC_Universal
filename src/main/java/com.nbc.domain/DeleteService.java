package com.nbc.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbc.core.RestClient;
import com.nbc.modals.UpdateUserModal;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteService extends RestClient {
    private final RequestSpecification deleteServiceRequest;

    public DeleteService() {
        this.deleteServiceRequest = getRequestSpecification();
    }


    public Response deleteUser(String path, int id) {
        deleteServiceRequest.pathParam("id", id);
        return deleteServiceRequest.delete(path + "/{id}");
    }
}
