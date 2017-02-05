package org.njit.cs.cucumber.java.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.*;
import org.junit.runner.RunWith;
import org.njit.cs.junit.java.test.JUnitAppTest;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@cucumber.api.CucumberOptions(
		plugin = {"pretty", "html:target/cucumber-html-report", "json:target/cucumber-json-report.json"/*,"json:target/cucumber-html-report/cucumber-json-report.json"*/},
		features = "src/test/resources/com/optum/cucumber"/*,
		tags = {"@senat"}*/
		)
public class CucumberJunitRunnerTest {
			
}
