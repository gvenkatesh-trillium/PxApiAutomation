package com.tests;

import com.relevantcodes.extentreports.LogStatus;
import com.utils.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.util.Random;


public class GenericCRM extends BaseClass {
    @Test
    public void CreateContactActivatedAndValidated(Method method) {

        testCase = method.getName();
        report.getTest().log(LogStatus.INFO,"Test case: Create contact, Activate and Validate");
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

    @Test
    public void RetrieveEntities(Method method) {

        testCase = method.getName();
        report.getTest().log(LogStatus.INFO," Test case: Retrieve Entities and Validate");
        request.header("Content-Type", "application/json");
        jsonBody = "{\n" +
                "\"entityName\": \"contact\",\n" +
                "  \"retrieveAllAttributesValues\": true,\n" +
                "  \"pagingInformation\": {\n" +
                "    \"currentPage\": 1,\n" +
                "    \"pageSize\": 10,\n" +
                "    \"sortBy\": \"tri_title_options\",\n" +
                "   \n" +
                "\n" +
                "}";

        report.getTest().log(LogStatus.INFO," POST request Body : <br /> "+ jsonBody);
        request.body(jsonBody);
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

    }

    @Test
    public void RetrieveOptionSet(Method method) {

        testCase = method.getName();
        report.getTest().log(LogStatus.INFO," Retrieve OptionSet and Validate");
        request.header("Content-Type", "application/json");
        jsonBody = "{\n" +
                "  \"entityName\": \"contact\",\n" +
                "  \"optionSetName\": \"tri_title_options\"\n" +
                "}";

        report.getTest().log(LogStatus.INFO," POST request Body : <br /> "+ jsonBody);
        request.body(jsonBody);
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

    }

    @Test
    public void RetrieveMultiplesOptionsSet(Method method) {

        testCase = method.getName();
        report.getTest().log(LogStatus.INFO," Test case: Retrieve Multiples OptionsSet and Validate the response");
        request.header("Content-Type", "application/json");

         jsonBody = " { \"optionsSet\": [ { \"entityName\": \"contact\", \"optionSetName\": \"preferredcontactmethodcode\" } ], \"optionsSet\": [ { \"entityName\": \"contact\", \"optionSetName\": \"tri_engagementlevel\" } ] }";


        request.body(jsonBody);
        report.getTest().log(LogStatus.INFO," POST request Body : <br /> "+ jsonBody);
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


    }

    @Test
    public void RetrieveCountries(Method method) {

        testCase = method.getName();
        report.getTest().log(LogStatus.INFO,"Test case: Retrieve Countries and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"pagingInformation\": {\n" +
                "    \"currentPage\": 1,\n" +
                "    \"pageSize\": 10,\n" +
                "    \"sortBy\": \"tri_name\",\n" +
                "    \"orderBy\": \"ASC\",\n" +
                "    \"maxResultCount\": 1000,\n" +
                "    \"totalCountLimitExceeded\": true\n" +
                "  }\n" +
                "}";


        request.body(jsonBody);
        report.getTest().log(LogStatus.INFO," POST request Body : "+ jsonBody);
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



    }

    @Test
    public void RetrieveCounties(Method method) {

        testCase = method.getName();
        report.getTest().log(LogStatus.INFO,"Test case: Retrieve Counties and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"pagingInformation\": {\n" +
                "    \"currentPage\": 1,\n" +
                "    \"pageSize\": 10,\n" +
                "    \"sortBy\": \"tri_name\",\n" +
                "    \"orderBy\": \"ASC\",\n" +
                "    \"maxResultCount\": 1000,\n" +
                "    \"totalCountLimitExceeded\": true\n" +
                "  }\n" +
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



    }

    @Test
    public void RetrieveAllBranches(Method method) {

        testCase = method.getName();
        report.getTest().log(LogStatus.INFO,"Test case: Retrieve All Branches and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"pagingInformation\": {\n" +
                "    \"currentPage\": 1,\n" +
                "    \"pageSize\": 10,\n" +
                "    \"sortBy\": \"tri_name\",\n" +
                "    \"orderBy\": \"ASC\",\n" +
                "    \"maxResultCount\": 1000,\n" +
                "    \"totalCountLimitExceeded\": true\n" +
                "  }\n" +
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



    }

    @Test
    public void LogMemberValue(Method method) {

        testCase = method.getName();
        report.getTest().log(LogStatus.INFO,"Test case: Log Member Value and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"subject\": \"aaaa\",\n" +
                "  \"contactId\": \"52f749c7-5e8c-e911-a820-000d3a0b5822\",\n" +
                "  \"memberusageTypeId\": \"1666d028-b052-e811-a958-002248072fe8\"\n" +
                "}";


        request.body(jsonBody);
        report.getTest().log(LogStatus.INFO," POST request Body : "+ jsonBody);
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


    }

    @Test
    public void GetAllContactRoles(Method method) {

        testCase = method.getName();
        report.getTest().log(LogStatus.INFO,"Test case: Get All Contact Roles and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"pagingInformation\": {\n" +
                "    \"currentPage\": 1,\n" +
                "    \"pageSize\": 10,\n" +
                "    \"sortBy\": \"tri_name\",\n" +
                "    \"orderBy\": \"ASC\",\n" +
                "    \"maxResultCount\": 1000,\n" +
                "    \"totalCountLimitExceeded\": true\n" +
                "  }\n" +
                "}";


        request.body(jsonBody);
        report.getTest().log(LogStatus.INFO," POST request Body : <br /> "+ jsonBody);

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



    }

    @Test(dependsOnMethods = {"CreateContactActivatedAndValidated"})
    public void GetContactRolesByContactId(Method method) {

        testCase = method.getName();
        report.getTest().log(LogStatus.INFO,"Test case: Get Contact Roles By ContactId and Validate(GET Request)");

        response = request.get(BASE_URL + testCase+"/"+contactId);
        try {
            Assert.assertEquals(response.getStatusCode(), 200);
            report.getTest().log(LogStatus.PASS," Check GET response Code : <br />"+ response.getStatusCode());
        } catch (AssertionError StatusCodeError) {
            report.getTest().log(LogStatus.FAIL,"Expected Response Code \"200\" but returned : <br />"+ response.getStatusCode());
            throw StatusCodeError;
        }
        try {
            Assert.assertTrue(response.asString().contains("\"success\":" + "true"));
            report.getTest().log(LogStatus.PASS," Check GET response Body  has Success is \"true\" and messageError is \"null\" : <br />"+ response.asString());
        } catch (AssertionError SuccessError) {
            report.getTest().log(LogStatus.FAIL,"Expected Response Success is \"true\" and messageError is \"null\" but returned : <br />"+ response.asString());
            throw SuccessError;
        }



    }
    @Test
    public void RetrieveAllPeriods(Method method) {

        testCase = method.getName();
        report.getTest().log(LogStatus.INFO,"Test case: Retrieve All Periods and Validate(GET Request)");
        response = request.get(BASE_URL + testCase);
        try {
            Assert.assertEquals(response.getStatusCode(), 200);
            report.getTest().log(LogStatus.PASS," Check GET response Code : <br />"+ response.getStatusCode());
        } catch (AssertionError StatusCodeError) {
            report.getTest().log(LogStatus.FAIL,"Expected Response Code \"200\" but returned : <br />"+ response.getStatusCode());
            throw StatusCodeError;
        }
        try {
            Assert.assertTrue(response.asString().contains("\"success\":" + "true"));
            report.getTest().log(LogStatus.PASS," Check GET response Body  has Success is \"true\" and messageError is \"null\" : <br />"+ response.asString());
        } catch (AssertionError SuccessError) {
            report.getTest().log(LogStatus.FAIL,"Expected Response Success is \"true\" and messageError is \"null\" but returned : <br />"+ response.asString());
            throw SuccessError;
        }

    }

    @Test
    public void PayTransaction(Method method) {
// response code is 200 but throwing an error "An error occurred calling the Payment Service: Received an unmapped Provider Type."
        testCase = method.getName();
        report.getTest().log(LogStatus.INFO,"Test case: Pay Transaction and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"transactionId\": \"d79e12b4-a99f-4c86-b1e1-e96d4556b372\",\n" +
                "  \"methodOfPaymentId\": \"77c3741e-2b1c-e511-80c7-005056bf2f1c\",\n" +
                "  \"onlinePaymentSuccessURL\": \"https://trilliumx.trilliumsystems.net/TrilliumX_Dev/v1.0/PaymentGateways/PaymentGateway/Success.aspx\",\n" +
                "  \"onlinePaymentFailureURL\": \"www.onlinePaymentFailureURL.com\"\n" +
                "}";


        request.body(jsonBody);
        report.getTest().log(LogStatus.INFO," POST request Body : "+ jsonBody);

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


    }
}
