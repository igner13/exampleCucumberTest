package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import implementation.UserCreationImpl;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static enums.APIs.*;
import static enums.Params.*;
import static io.restassured.RestAssured.requestSpecification;
import static org.assertj.core.api.Assertions.assertThat;

public class UserCreationSteps {

    UserCreationImpl userCreation = new UserCreationImpl();
    HashMap<String, String> userInfo;
    Response response;
    String userId;
    String updatedSalary;

    @Given("user name is: (.*), salary is: (.*) and age is (.*)")
    public void userNameIsUserNameSalaryIsSalaryAndAgeIsAge(String name, String salary, String age) {
        userInfo = userCreation.prepareRequestBody(name, salary, age);
    }

    @And("user is created in restapiexample system")
    public void userIsCreatedInSystem() {
        requestSpecification = userCreation.preparePostRequest(userInfo, CREATE);
        response = userCreation.postRequest(requestSpecification);

        Map<String, Object> parsedResponse = response.jsonPath().getMap(DATA.getParam());

        userId = String.valueOf(parsedResponse.get(ID.getParam()));
        String userSalary = String.valueOf((parsedResponse.get(SALARY.getParam())));

        assertThat(userSalary.equals(userInfo.get(SALARY.getParam())));
    }

    @When("salary is changed to (.*)")
    public void salaryIsChangedToNewSalary(String newSalary) {
        updatedSalary = newSalary;
        userInfo.put(SALARY.getParam(), newSalary);

        requestSpecification = userCreation.preparePutRequest(userInfo, UPDATE, userId);
        userCreation.putRequest(requestSpecification);
    }

    @Then("user should be updated in restapiexample system")
    public void userShouldBeUpdatedInSystem() {
        requestSpecification = userCreation.prepareGetRequest(EMPLOYEE, userId);
        response = userCreation.getRequest(requestSpecification);

        Map<String, String> parsedResponse = response.jsonPath().getMap(DATA.getParam());
        String userSalary = parsedResponse.get(EMPLOYEE_SALARY.getParam());

        assertThat(userSalary.equals(updatedSalary));
    }
}
