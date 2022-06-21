package co.gorest.CURDTest;


import co.gorest.constants.EndPoints;
import co.gorest.model.UsersPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;


import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;


public class UsersSteps {

    @Step("Creating user with name : {0},gender:{1},email:{2} and status : {3}")
    public ValidatableResponse createUser(String name, String gender, String email, String status) {

        UsersPojo usersPojo = new UsersPojo();
        usersPojo.setName(name);
        usersPojo.setGender(gender);
        usersPojo.setEmail(email);
        usersPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer c4f34459347dc40079323c89fd1db2e9987ad300d49946749a28c89b1bf318ba")
                .body(usersPojo)
                .when()
                .post(EndPoints.GET_USERS)
                .then();
    }


    @Step("Getting user information with userId : {0}")
    public HashMap<String, Object> getSingleUserInfoMap(int userId) {
        HashMap<String, Object> userMap = SerenityRest.given().log().all()
                .when()
                .header("Authorization", "Bearer c4f34459347dc40079323c89fd1db2e9987ad300d49946749a28c89b1bf318ba")
                .pathParam("userID", userId)
                .get(EndPoints.GET_SINGLE_USER)
                .then()
                .statusCode(200)
                .extract()
                .path("");
        return userMap;

    }

    @Step("Updating user with id : {0},name:{1},gender:{2} email : {3} and status:{4}")
    public ValidatableResponse updateUser(int id,String name, String gender, String email, String status){
        UsersPojo usersPojo = new UsersPojo();
        usersPojo.setName(name);
        usersPojo.setGender(gender);
        usersPojo.setEmail(email);
        usersPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer c4f34459347dc40079323c89fd1db2e9987ad300d49946749a28c89b1bf318ba")
                .pathParam("userID", id)
                .body(usersPojo)
                .when()
                .put(EndPoints.GET_SINGLE_USER)
                .then();    }

    @Step("Deleting student information with studentId: {0}")
    public ValidatableResponse deleteUser(int id){

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer c4f34459347dc40079323c89fd1db2e9987ad300d49946749a28c89b1bf318ba")
                .pathParam("userID", id)
                .when()
                .delete(EndPoints.GET_SINGLE_USER)
                .then();

    }
    @Step("Getting user information with userId: {0}")
    public ValidatableResponse getUserInfoById(int id){
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer c4f34459347dc40079323c89fd1db2e9987ad300d49946749a28c89b1bf318ba")
                .pathParam("userID", id)
                .when()
                .get(EndPoints.GET_SINGLE_USER)
                .then();


    }
    public HashMap<String ,Object> getUserInfoByEmail (String email){
        String p1 = "findAll{it.email=='";
        String p2 = "'}.get(0)";
        HashMap<String, Object> userMap = SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_USERS)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + email + p2);
        return userMap;


    }

}
