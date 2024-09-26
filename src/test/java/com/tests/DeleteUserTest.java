package com.tests;

import com.nbc.core.RestClient;
import com.nbc.domain.DeleteService;
import com.nbc.utils.CommonUtils;
import com.nbc.utils.ExcelUtils;
import com.nbc.utils.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DeleteUserTest {
    RestClient restClient;
    DeleteService deleteService;
    CommonUtils utils;
    static {
        LogManager.getLogger(DeleteUserTest.class);
    }

    @BeforeMethod
    public void DeleteUser() {
        restClient = new RestClient();
        deleteService = new DeleteService();
        utils=new CommonUtils();
    }

    @Test(dataProvider = "deleteUserData")
    public void deleteUser(String id) {
        restClient.response = deleteService.deleteUser(PropertyReader.getProperty(RestClient.propertyFilePath, "posts"), Integer.parseInt(id));
        utils.verifyStatusCode(restClient.response,200);
        Assert.assertEquals(restClient.response.body().toString(), "{}");
    }

    @Test
    public void deleteNonExistentUser() {
        restClient.response = deleteService.deleteUser(PropertyReader.getProperty(RestClient.propertyFilePath, "posts"), 5432);
        utils.verifyStatusCode(restClient.response,200);
        Assert.assertEquals(restClient.response.body().asString(), "{}");
    }

    @Test
    public void deleteAlreadyDeletedUser() {
        //User deleted for the first time
        restClient.response = deleteService.deleteUser(PropertyReader.getProperty(RestClient.propertyFilePath, "posts"), 1);

        //Trying to delete the user which was deleted already
        restClient.response = deleteService.deleteUser(PropertyReader.getProperty(RestClient.propertyFilePath, "posts"), 1);
        utils.verifyStatusCode(restClient.response,200);
        Assert.assertEquals(restClient.response.body().asString(), "{}");
    }

    @Test
    public void deleteInvalidUserId() {
        restClient.response = deleteService.deleteUser(PropertyReader.getProperty(RestClient.propertyFilePath, "posts"), -1);
        utils.verifyStatusCode(restClient.response,200);
        Assert.assertEquals(restClient.response.body().asString(), "{}");
    }

    @DataProvider(name = "deleteUserData")
    public Object[][] deleteUserData() {
        return ExcelUtils.readExcel("src/test/resources/UserData.xlsx", "DeleteUsersData");
    }
}
