package com.tests;

import com.nbc.core.RestClient;
import com.nbc.domain.PatchService;
import com.nbc.modals.PartialUpdateUserModal;
import com.nbc.utils.CommonUtils;
import com.nbc.utils.ExcelUtils;
import com.nbc.utils.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PartialUpdateUserTest {
    RestClient restClient;
    PatchService patchService;
    CommonUtils utils;
    static {
        LogManager.getLogger(CreateUserTest.class);
    }

    @BeforeMethod
    public void partialUpdateUser()
    {
        restClient=new RestClient();
        patchService=new PatchService();
        utils = new CommonUtils();

    }

    @Test(dataProvider = "partialUpdateUserData")
    public void partialUpdateUser(String id, String userId, String title, String body)
    {
        PartialUpdateUserModal partialUpdateUserModal=new PartialUpdateUserModal();

        if(!id.equalsIgnoreCase("NA"))
        {
            partialUpdateUserModal.setId(Integer.parseInt(id));
        }
        if(!userId.equalsIgnoreCase("NA"))
        {
            partialUpdateUserModal.setUserId(Integer.parseInt(userId));
        }
        if(!title.equalsIgnoreCase("NA"))
        {
            partialUpdateUserModal.setTitle(title);
        }
        if(!body.equalsIgnoreCase("NA"))
        {
            partialUpdateUserModal.setBody(body);
        }

        restClient.response = patchService.partialUpdateUser(PropertyReader.getProperty(RestClient.propertyFilePath,"posts"),Integer.parseInt(id),partialUpdateUserModal);
        utils.verifyStatusCode(restClient.response,200);
        Assert.assertNotNull(restClient.response.body().asString());
    }

    @Test
    public void partialUpdateUserForNonExistentId()
    {
        PartialUpdateUserModal partialUpdateUserModal=new PartialUpdateUserModal();
        partialUpdateUserModal.setTitle("New title");
        restClient.response = patchService.partialUpdateUser(PropertyReader.getProperty(RestClient.propertyFilePath,"posts"),99999,partialUpdateUserModal);
        utils.verifyStatusCode(restClient.response,200);
        Assert.assertNotNull(restClient.response.body().asString());
        utils.userValidatesThePropertyFromResponse(restClient.response,"title","New title");
    }

    @Test
    public void partialUpdateUserWithInvalidData()
    {
        PartialUpdateUserModal partialUpdateUserModal=new PartialUpdateUserModal();
        partialUpdateUserModal.setBody(null);
        restClient.response = patchService.partialUpdateUser(PropertyReader.getProperty(RestClient.propertyFilePath,"posts"),2,partialUpdateUserModal);
        utils.verifyStatusCode(restClient.response,200);
        Assert.assertNotNull(restClient.response.body().asString());
    }

    @Test
    public void partialUpdateUserWithEmptyBody()
    {
        PartialUpdateUserModal partialUpdateUserModal=new PartialUpdateUserModal();
        restClient.response = patchService.partialUpdateUser(PropertyReader.getProperty(RestClient.propertyFilePath,"posts"),2,partialUpdateUserModal);
        utils.verifyStatusCode(restClient.response,200);
        Assert.assertNotNull(restClient.response.body().asString());
    }

    @DataProvider(name="partialUpdateUserData")
    public Object[][] partialUpdateUserData()
    {
        return ExcelUtils.readExcel("src/test/resources/UserData.xlsx","PartialUpdateUsersData");
    }
}
