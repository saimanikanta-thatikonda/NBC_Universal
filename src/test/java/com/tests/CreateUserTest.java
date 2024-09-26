package com.tests;

import com.nbc.modals.UserModal;
import com.nbc.utils.CommonUtils;
import com.nbc.utils.ExcelUtils;
import com.nbc.utils.PropertyReader;
import com.nbc.core.RestClient;
import com.nbc.domain.PostService;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class CreateUserTest {
    RestClient restClient;
    PostService postService;
    CommonUtils utils;
    static {
        LogManager.getLogger(CreateUserTest.class);
    }

    @BeforeMethod
    public void CreateUser()
    {
        restClient=new RestClient();
        postService=new PostService();
        utils= new CommonUtils();
    }

    @Test(dataProvider = "userData")
    public void createUserWithValidDetails(String id,String title,String body)
    {
        UserModal userModal=new UserModal(Integer.parseInt(id),title,body);
        restClient.response = postService.createUser(PropertyReader.getProperty(RestClient.propertyFilePath,"posts"),userModal);
        System.out.println(restClient.response.body().asString());
        utils.verifyStatusCode(restClient.response,201);
        utils.userValidatesThePropertyFromResponse(restClient.response,"userId",id);
        utils.userValidatesThePropertyFromResponse(restClient.response,"title",title);
        utils.userValidatesThePropertyFromResponse(restClient.response,"body",body);
    }

    @Test(dataProvider = "invalidUserData")
    public void createUserWithInvalidData(String id,String title,String body)
    {
        UserModal userModal = null;
        if(title.equalsIgnoreCase("NA"))
        {
            userModal =new UserModal(Integer.parseInt(id),null,body);
        }
        else if(body.equalsIgnoreCase("NA"))
        {
            userModal =new UserModal(Integer.parseInt(id),title,null);
        }
        else{
            userModal =new UserModal(Integer.parseInt(id),title,body);
        }
        restClient.response = postService.createUser(PropertyReader.getProperty(RestClient.propertyFilePath,"posts"),userModal);
        System.out.println(restClient.response.body().asString());
        utils.verifyStatusCode(restClient.response,201);
    }

    @DataProvider(name="userData")
    public Object[][] userData()
    {
        return ExcelUtils.readExcel("src/test/resources/UserData.xlsx","UsersData");
    }

    @DataProvider(name="invalidUserData")
    public Object[][] invalidUserData()
    {
        return ExcelUtils.readExcel("src/test/resources/UserData.xlsx","InvalidUsers");
    }
}
