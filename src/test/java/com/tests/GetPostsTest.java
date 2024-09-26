package com.tests;

import com.nbc.utils.CommonUtils;
import com.nbc.utils.PropertyReader;
import com.nbc.core.RestClient;
import com.nbc.domain.GetService;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetPostsTest {
    RestClient restClient;
    GetService getService;
    CommonUtils utils;
    static {
        LogManager.getLogger(GetPostsTest.class);
    }

    @BeforeMethod
    public void GetPosts()
    {
        getService = new GetService();
        restClient = new RestClient();
        utils = new CommonUtils();
    }

    @Test
    public void getPosts()
    {
        restClient.response=getService.getPosts(PropertyReader.getProperty("config.properties","posts"));
        utils.verifyStatusCode(restClient.response,200);
        Assert.assertNotNull(restClient.response.body().asString());
    }
}
