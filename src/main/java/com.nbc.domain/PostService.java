package com.nbc.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbc.core.RestClient;
import com.nbc.modals.UserModal;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostService extends RestClient{
    private final RequestSpecification postServiceRequest;

    public PostService()
    {
        this.postServiceRequest=getRequestSpecification();
    }


    public Response createUser(String path,UserModal userModal)
    {
        return postServiceRequest.body(createUserRequestBody(userModal)).post(path);
    }

    /**
     * This method is used to create a request body
     *
     * @param  - Contains the details of a pet
     * @return - Returns a string which contains the json request of pet details
     */
    public String createUserRequestBody(UserModal userModal) {
        String requestBody = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            requestBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userModal);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return requestBody;
    }
}
