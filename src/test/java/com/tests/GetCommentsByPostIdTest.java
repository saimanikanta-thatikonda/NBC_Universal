package com.tests;

import com.nbc.core.RestClient;
import com.nbc.domain.GetService;
import com.nbc.utils.CommonUtils;
import com.nbc.utils.ExcelUtils;
import com.nbc.utils.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetCommentsByPostIdTest {
    RestClient restClient;
    GetService getService;
    CommonUtils utils;
    static {
        LogManager.getLogger(GetPostsByIdTest.class);
    }

    @BeforeMethod
    public void GetCommentsByPostId()
    {
        getService = new GetService();
        restClient = new RestClient();
        utils = new CommonUtils();
    }

    @Test(dataProvider = "postId")
    public void getCommentsByPostId(String postId)
    {
        String resourcePath = PropertyReader.getProperty(RestClient.propertyFilePath,"comments");
        restClient.response=getService.getCommentsByPostId(resourcePath,Integer.parseInt(postId));
        utils.verifyStatusCode(restClient.response,200);
        Assert.assertNotNull(restClient.response.body().asString());
        Assert.assertTrue(restClient.response.body().asString().contains("postId"));
    }

    @Test
    public void getCommentsByPostIdWithNoComments()
    {
        String resourcePath = PropertyReader.getProperty(RestClient.propertyFilePath,"comments");
        restClient.response=getService.getCommentsByPostId(resourcePath,99999);
        utils.verifyStatusCode(restClient.response,200);
        Assert.assertNotNull(restClient.response.body().asString());
        Assert.assertFalse(restClient.response.body().asString().contains("postId"));
    }

    @Test
    public void getCommentsForInvalidPostId()
    {
        String resourcePath = PropertyReader.getProperty(RestClient.propertyFilePath,"comments");
        restClient.response=getService.getCommentsByPostId(resourcePath,-1);
        utils.verifyStatusCode(restClient.response,200);
        Assert.assertNotNull(restClient.response.body().asString());
        Assert.assertFalse(restClient.response.body().asString().contains("postId"));
    }

    @DataProvider(name="postId")
    public Object[][] postId()
    {
        return ExcelUtils.readExcel("src/test/resources/UserData.xlsx","PostId");
    }
}
