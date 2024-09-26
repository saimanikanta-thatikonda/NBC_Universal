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

public class GetPostsByIdTest {
    RestClient restClient;
    GetService getService;
    CommonUtils utils;
    static {
        LogManager.getLogger(GetPostsByIdTest.class);
    }

    @BeforeMethod
    public void GetPostsById()
    {
        getService = new GetService();
        restClient = new RestClient();
        utils = new CommonUtils();
    }

    @Test(dataProvider = "userId")
    public void getPostsById(String id)
    {
        String resourcePath = PropertyReader.getProperty("config.properties","posts");
        restClient.response=getService.getPostsById(resourcePath,Integer.parseInt(id));
        utils.verifyStatusCode(restClient.response,200);
        Assert.assertNotNull(restClient.response.body().asString());
        utils.userValidatesThePropertyFromResponse(restClient.response,"id",id);
    }

    @Test(dataProvider = "userId")
    public void getPostsByIdComments(String id)
    {
        String resourcePath = PropertyReader.getProperty("config.properties","posts");
        restClient.response=getService.getPostsByIdComments(resourcePath,Integer.parseInt(id));
        utils.verifyStatusCode(restClient.response,200);
        Assert.assertNotNull(restClient.response.body().asString());
        Assert.assertTrue(restClient.response.body().asString().contains("email"));
    }

    @Test
    public void getPostsByIdWithNoComments()
    {
        String resourcePath = PropertyReader.getProperty("config.properties","posts");
        restClient.response=getService.getPostsByIdComments(resourcePath,99999);
        utils.verifyStatusCode(restClient.response,200);
        Assert.assertNotNull(restClient.response.body().asString());
        Assert.assertFalse(restClient.response.body().asString().contains("email"));
    }

    @Test
    public void getPostsByInvalidId()
    {
        String resourcePath = PropertyReader.getProperty("config.properties","posts");
        restClient.response=getService.getPostsByIdComments(resourcePath,-1);
        utils.verifyStatusCode(restClient.response,200);
        Assert.assertNotNull(restClient.response.body().asString());
        Assert.assertFalse(restClient.response.body().asString().contains("email"));
    }

    @DataProvider(name="userId")
    public Object[][] userId()
    {
        return ExcelUtils.readExcel("src/test/resources/UserData.xlsx","UserId");
    }
}
