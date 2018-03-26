package com.bhanu;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.annotations.Factory;

import com.cucumber.CucumberFactoryBuilder;

import java.io.File;


public class TestFactory {

    @Factory
    public Object[] create() {
        Object[] x = new CucumberFactoryBuilder().create(new File("src"));
        System.out.println("Bhanu x is:"+x.length);
        return x;
    }

    @Given("^the iTunes file (.*)$")
    public void openItunesFile(String file) {
        System.out.println("Opening " + file);
    }

    @When("^searching for the artist (.*)$")
    public void searching_for_the_artist(String artist) {
        // Express the Regexp above with the code you wish you had
//        throw new PendingException();
    	System.out.println("artist "+artist);
    }

    @Then("^there should be (\\d+) albums found$")
    public void there_should_be_albums_found(int count) {
        // Express the Regexp above with the code you wish you had
//        throw new PendingException();
    	System.out.println("count "+count);
    }

}
