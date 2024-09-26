package com.nbc.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbc.core.RestClient;
import com.nbc.modals.PartialUpdateUserModal;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PatchService extends RestClient {
    private final RequestSpecification patchServiceRequest;

    public PatchService() {
        this.patchServiceRequest = getRequestSpecification();
    }


    public Response partialUpdateUser(String path, int id, PartialUpdateUserModal partialUpdateUserModal) {
        patchServiceRequest.pathParam("id", id);
        return patchServiceRequest.body(partialUpdateUserRequestBody(partialUpdateUserModal)).patch(path + "/{id}");
    }

    /**
     * This method is used to create a request body
     *
     * @param - Contains the details of a pet
     * @return - Returns a string which contains the json request of pet details
     */
    public String partialUpdateUserRequestBody(PartialUpdateUserModal partialUpdateUserModal) {
        String requestBody = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            requestBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(partialUpdateUserModal);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
        return requestBody;
    }
}
