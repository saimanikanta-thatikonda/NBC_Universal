package com.nbc.domain;

import com.nbc.core.RestClient;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetService extends RestClient{
    private final RequestSpecification getServiceRequest;

    public GetService()
    {
        this.getServiceRequest=getRequestSpecification();
    }

    public Response getPosts(String path)
    {
        return getServiceRequest.get(path);
    }

    public Response getPostsById(String path,int id)
    {
        getServiceRequest.pathParam("id",id);
        return getServiceRequest.get(path+"/{id}");
    }
    public Response getPostsByIdComments(String path,int id)
    {
        getServiceRequest.pathParam("id",id);
        return getServiceRequest.get(path+"/{id}/comments");
    }
    public Response getCommentsByPostId(String path,int postId)
    {
        getServiceRequest.queryParam("postId",postId);
        return getServiceRequest.get(path);
    }


}
