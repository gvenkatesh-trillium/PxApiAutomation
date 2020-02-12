package com.utils;

import com.relevantcodes.extentreports.LogStatus;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;


public abstract class BaseClass {
    public static String BASE_URL = "https://trilliumx.azurewebsites.net/TrilliumX_Dev/v1.0/Services/TrilliumWebAPI/";
    public static RequestSpecification request = RestAssured.given().auth().basic("77628226-A277-47B5-9409-69D0F26A2DC2", "qwerty123");
    public static ExtentTestManager report;
    public static Response response;

    public static String contactId;
    public static String membershipId;
    public static String organisationId;
    public static String addressId;
    public static String eMail;
    public static String testCase;
    public static String jsonBody;
    public static String rNum;



    @BeforeMethod
    public void beforeMethod(Method method) {

        ExtentTestManager.startTest(method.getName());
    }

    @AfterMethod
    protected void afterMethod(ITestResult result)
    {
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
        } else {
//            ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
        }

        ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
        ExtentManager.getReporter().flush();
    }


    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }
}