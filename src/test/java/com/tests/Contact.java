package com.tests;


import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.utils.BaseClass;

import java.lang.reflect.Method;
import java.util.Random;


public class Contact extends BaseClass {

    @Test(priority=1)
    public void CreateContactActivatedAndValidated(Method method) {

        testCase = method.getName();
        report.getTest().log(LogStatus.INFO," Test case : Create contact, Activate and Validate");
        request.header("Content-Type", "application/json");
         rNum = String.valueOf(new Random().nextInt(100000 - 10 + 1) + 1);
        jsonBody = "{\n" +
                "  \n" +
                "  \"firstName\": \"API Automation FirstName\",\n" +
                "  \"lastName\": \"API Automation LasttName\",\n" +
                "\n" +
                "  \"personalemail\": \"personalemail."+rNum+"GV@Automation.com\",\n" +
                "  \"workemail\": \"string\",\n" +
                "  \"preferredEmailAddress\": {\n" +
                "    \"value\": 167410000,\n" +
                "    \"description\": \"work\"\n" +
                "  },\n" +
                "\n" +
                "  \"title\": {\n" +
                "    \"value\": 167410032,\n" +
                "    \"description\": \"string\"\n" +
                "},\n" +
                " \n" +
                "\n" +
                "  \"jobRoleOther\": \"API jobRoleOther\",\n" +
                "  \"jobTitle\": \"API jobTitle\",\n" +
                "  \"organisationText\": \"OrganisationText\"\n" +
                "}";
        request.body(jsonBody);
        report.getTest().log(LogStatus.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);

        try {
            Assert.assertEquals(response.getStatusCode(), 200);
            report.getTest().log(LogStatus.PASS," Check POST response Code : <br />"+ response.getStatusCode());
        } catch (AssertionError StatusCodeError) {
            report.getTest().log(LogStatus.FAIL,"Expected Response Code \"200\" but returned : <br />"+ response.getStatusCode());
            throw StatusCodeError;
        }
        try {
            Assert.assertTrue(response.asString().contains("\"success\":" + "true"));
            report.getTest().log(LogStatus.PASS," Check POST response Body  has Success is \"true\" and messageError is \"null\" : <br />"+ response.asString());
        } catch (AssertionError SuccessError) {
            report.getTest().log(LogStatus.FAIL,"Expected Response Success is \"true\" and messageError is \"null\" but returned : <br />"+ response.asString());
            throw SuccessError;
        }

        contactId =  response.asString().split("\"")[9];
        eMail = response.asString().split("\"")[17];
        if(!eMail.contains("@")){
            report.getTest().log(LogStatus.FAIL," no email found in response : <br/>"+ response.asString());
        }
        Assert.assertTrue(response.asString().contains(contactId));

    }

    @Test(priority=2, dependsOnMethods = {"CreateContactActivatedAndValidated"})
    public void UpdateContact(Method method){
        testCase = method.getName();
        rNum = String.valueOf(new Random().nextInt(100000 - 10 + 1) + 1);
        report.getTest().log(LogStatus.INFO,"Test case:  Updating  firstName, lastName and eMail for id : <br />"+ contactId);
        request.header("Content-Type", "application/json");
        jsonBody = "{\n" +
                "  \"clearContactDataForEmptyParams\": true,\n" +
                " \"id\": \"" + contactId + "\",\n" +
                "  \"validateContact\": true,\n" +
                "  \"lastName\": \"UpDateContactLastName\",\n" +
                "  \"middleName\": \"UpDateMiddleName\",\n" +
                "  \"personalemail\": \"updateContact" + rNum + "@API.com\"\n" +
                " \n" +
                "}";
        request.body(jsonBody);
        report.getTest().log(LogStatus.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL+ testCase);

        try {
            Assert.assertEquals(response.getStatusCode(), 200);
            report.getTest().log(LogStatus.PASS," Check POST response Code : <br />"+ response.getStatusCode());
        } catch (AssertionError StatusCodeError) {
            report.getTest().log(LogStatus.FAIL,"Expected Response Code \"200\" but returned : <br />"+ response.getStatusCode());
            throw StatusCodeError;
        }
        try {
            Assert.assertTrue(response.asString().contains("\"success\":" + "true"));
            report.getTest().log(LogStatus.PASS," Check POST response Body  has Success is \"true\" and messageError is \"null\" : <br />"+ response.asString());
        } catch (AssertionError SuccessError) {
            report.getTest().log(LogStatus.FAIL,"Expected Response Success is \"true\" and messageError is \"null\" but returned : <br />"+ response.asString());
            throw SuccessError;
        }


    }
    @Test(priority=3, dependsOnMethods = {"CreateContactActivatedAndValidated"})
    public void RetrieveContact(Method method){
        testCase = method.getName();
        report.getTest().log(LogStatus.INFO," Test case : Retrieve Contact by eMail and verify the response");
        request.header("Content-Type", "application/json");
        jsonBody = "{\n" +
                "  \"email\":\""+ eMail + "\",\n" +
                " \n" +
                "}";
        report.getTest().log(LogStatus.INFO," POST request Body : <br />"+ jsonBody);
        request.body(jsonBody);
        response = request.post(BASE_URL+ testCase);
        try {
            Assert.assertEquals(response.getStatusCode(), 200);
            report.getTest().log(LogStatus.PASS," Check POST response Code : <br />"+ response.getStatusCode());
        } catch (AssertionError StatusCodeError) {
            report.getTest().log(LogStatus.FAIL,"Expected Response Code \"200\" but returned : <br />"+ response.getStatusCode());
            throw StatusCodeError;
        }
        try {
            Assert.assertTrue(response.asString().contains("\"success\":" + "true"));
            report.getTest().log(LogStatus.PASS," Check POST response Body  has Success is \"true\" and messageError is \"null\" : <br />"+ response.asString());
        } catch (AssertionError SuccessError) {
            report.getTest().log(LogStatus.FAIL,"Expected Response Success is \"true\" and messageError is \"null\" but returned : <br />"+ response.asString());
            throw SuccessError;
        }

    }

//    @Test(dependsOnMethods = {"CreateContactActivatedAndValidated"})
    @Test(priority=4)
    public void CheckContactExists(Method method) {

        testCase = method.getName();
        report.getTest().log(LogStatus.INFO," Test case: Check Contact Exists by eMail and verify the response");
        request.header("Content-Type", "application/json");
        jsonBody = "{\n" +
                "  \"email\": \"test@Automation1.com\"\n" +
                " \n" +
                "}";
        report.getTest().log(LogStatus.INFO," POST request Body : <br />"+ jsonBody);
        request.body(jsonBody);
        response = request.post(BASE_URL+ testCase);

        try {
            Assert.assertEquals(response.getStatusCode(), 200);
            report.getTest().log(LogStatus.PASS," Check POST response Code : <br />"+ response.getStatusCode());
        } catch (AssertionError StatusCodeError) {
            report.getTest().log(LogStatus.FAIL,"Expected Response Code \"200\" but returned : <br />"+ response.getStatusCode());
            throw StatusCodeError;
        }
        try {
            Assert.assertTrue(response.asString().contains("\"success\":" + "true"));
            report.getTest().log(LogStatus.PASS," Check POST response Body  has Success is \"true\" and messageError is \"null\" : <br />"+ response.asString());
        } catch (AssertionError SuccessError) {
            report.getTest().log(LogStatus.FAIL,"Expected Response Success is \"true\" and messageError is \"null\" but returned : <br />"+ response.asString());
            throw SuccessError;
        }

//        System.out.println("Pretty Print = "+response.prettyPrint());
//        System.out.println("get body = "+response.getBody());
//        System.out.println("Header as list  = "+response.getHeaders().asList());






    }



}
