package co.gorest.cucumber.steps;

import co.gorest.CURDTest.UsersSteps;
import co.gorest.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class GorestStepDef {
    static ValidatableResponse response;
    static String email = null;
    static String name = null;
    static int id;
    static String gender1;
    static String status1;
    @Steps

    UsersSteps usersSteps;


    @When("^I create a new user by providing the information name \"([^\"]*)\"  gender \"([^\"]*)\" email \"([^\"]*)\" status \"([^\"]*)\"$")
    public void iCreateANewUserByProvidingTheInformationNameGenderEmailStatus(String _name, String gender, String _email, String status)  {
        email = TestUtils.getRandomValue() + _email;
        name = _name;
        response = usersSteps.createUser(_name, gender, email, status);
        id = response.log().all().extract().path("id");
        gender1 = response.log().all().extract().path("gender");
        status1 =response.log().all().extract().path("status");

        System.out.println(id);

    }

    @And("^I verify that the user with id has been created$")
    public void iVerifyThatTheUserWithIdHasBeenCreated() {
        response.statusCode(201);
        HashMap<String,Object> userMap =usersSteps.getSingleUserInfoMap(id);
        Assert.assertThat(userMap,hasValue(name));

    }

    @And("^I updated a name of the user created$")
    public void iUpdatedANameOfTheUserCreated() {
        name =   name+"_Updated";
        response =usersSteps.updateUser(id,name,gender1,email,status1);
    }

    @And("^I verify that the user name has been updated$")
    public void iVerifyThatTheUserNameHasBeenUpdated() {
        response.statusCode(200);
        HashMap<String,Object> userMap =usersSteps.getSingleUserInfoMap(id);
        Assert.assertThat(userMap,hasValue(name));
    }

    @And("^I delete user with same id$")
    public void iDeleteUserWithSameId() {
        usersSteps.deleteUser(id).statusCode(204);
    }

    @Then("^I verify that the user has been deleted$")
    public void iVerifyThatTheUserHasBeenDeleted() {
        usersSteps.getUserInfoById(id).statusCode(404);

    }
}
