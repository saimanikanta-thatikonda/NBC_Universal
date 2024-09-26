package com.tests;

import com.nbc.core.RestClient;
import com.nbc.domain.PostService;
import com.nbc.domain.PutService;
import com.nbc.modals.UpdateUserModal;
import com.nbc.modals.UserModal;
import com.nbc.utils.CommonUtils;
import com.nbc.utils.ExcelUtils;
import com.nbc.utils.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UpdateUserTest {
    RestClient restClient;
    PutService putService;
    CommonUtils utils;
    static {
        LogManager.getLogger(CreateUserTest.class);
    }

    @BeforeMethod
    public void UpdateUser()
    {
        restClient=new RestClient();
        putService=new PutService();
        utils = new CommonUtils();

    }

    @Test(dataProvider = "updateUserData")
    public void updateUser(String id,String userId,String title,String body)
    {
        UpdateUserModal updateUserModal=new UpdateUserModal(Integer.parseInt(id),Integer.parseInt(userId),title,body);
        restClient.response = putService.updateUser(PropertyReader.getProperty(RestClient.propertyFilePath,"posts"),Integer.parseInt(id),updateUserModal);
        utils.verifyStatusCode(restClient.response,200);
        utils.userValidatesThePropertyFromResponse(restClient.response,"id",id);
        utils.userValidatesThePropertyFromResponse(restClient.response,"userId",userId);
        utils.userValidatesThePropertyFromResponse(restClient.response,"title",title);
        utils.userValidatesThePropertyFromResponse(restClient.response,"body",body);
    }

    @Test
    public void updateUserWithEmptyBody()
    {
        UpdateUserModal userModal = null;
        restClient.response = putService.updateUser(PropertyReader.getProperty(RestClient.propertyFilePath,"posts"),2,userModal);
        utils.verifyStatusCode(restClient.response,500);
    }

    @DataProvider(name="updateUserData")
    public Object[][] updateUserData()
    {
        return ExcelUtils.readExcel("src/test/resources/UserData.xlsx","UpdateUsersData");
    }
}
