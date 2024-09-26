package com.nbc.core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.nbc.utils.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class RestClient {
    private static RequestSpecification request;
    public static String propertyFilePath = "config.properties";
    public Response response;

    public RestClient() {
        RestAssured.baseURI = PropertyReader.getProperty(propertyFilePath, "baseUrl");
        request = RestAssured.given();
        request.header("accept", "application/json");
        request.header("Content-Type", "application/json");
    }

    public static RequestSpecification getRequestSpecification() {
        return request;
    }
}
