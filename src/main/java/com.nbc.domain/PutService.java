package com.nbc.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbc.core.RestClient;
import com.nbc.modals.UpdateUserModal;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutService extends RestClient {
    private final RequestSpecification putServiceRequest;

    public PutService() {
        this.putServiceRequest = getRequestSpecification();
    }


    public Response updateUser(String path,int id, UpdateUserModal updateUserModal) {
        putServiceRequest.pathParam("id",id);
        return putServiceRequest.body(updateUserRequestBody(updateUserModal)).put(path+"/{id}");
    }

    /**
     * This method is used to create a request body
     *
     * @param - Contains the details of a pet
     * @return - Returns a string which contains the json request of pet details
     */
    public String updateUserRequestBody(UpdateUserModal updateUserModal) {
        String requestBody = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            requestBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(updateUserModal);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
        return requestBody;
    }
}