package org.njit.cs.cucumber.java.test;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FeatureStep {
	@Given("^I navigate to a site$")
	public void navigateToTheSite() throws Throwable {
		System.out.println("The given step is executed");
	}

	@When("^I click on a button$")
	public void clickOnButton() throws Throwable {
		System.out.println("The when step is executed");
	}

	@Then("^I check I am on the correct page$")
	public void checkTheCorrectPage() throws Throwable {
		System.out.println("The then step is executed");
	}
}
