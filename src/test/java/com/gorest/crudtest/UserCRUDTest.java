package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {
    static int userId;
    static String name = "testprime" + TestUtils.getRandomValue();
    static String gender = "male";
    static String email = TestUtils.getRandomValue() + "testprime@gmail.com";
    static String status = "active";

    @Test
    public void test001() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setGender(gender);
        userPojo.setEmail(email);
        userPojo.setStatus(status);

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .body(userPojo)
                .when()
                .post().then().extract().response();
        response.then().log().all().statusCode(201);
        userId = response.jsonPath().get("id");
        System.out.println("Id is: "+ userId);
    }

    @Test
    public void test002() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Access", "application/json")
                .header("Authorization", "Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .when()
                .get("/" +userId);
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void test003() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name + "_Updated");
        userPojo.setGender(gender);
        userPojo.setEmail(email);
        userPojo.setStatus(status);

        Response response = given()
                .header("Content-Type", "application.json")
                .header("Authorization", "Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .contentType(ContentType.JSON)
                .body(userPojo)
                .when()
                .put("/" +userId);
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void test004() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Access", "application/json")
                .header("Authorization", "Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .when()
                .get("/" +userId);
        response.then().statusCode(200);
        response.prettyPrint();

        String actualName = response.then().extract().body().path("name");
        Assert.assertEquals("Name is not updated", name + "_Updated", actualName);
        System.out.println("ActualName is: " + actualName);
        System.out.println("ExpectedName is: " + name + "_Updated");

    }


    @Test
    public void test005() {
        String token = "61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215";
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Access", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/" +userId);
        response.then().statusCode(204);
        response.prettyPrint();


    }


}



