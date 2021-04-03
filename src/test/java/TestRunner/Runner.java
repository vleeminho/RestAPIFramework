package TestRunner;

import org.junit.runner.RunWith;		
import cucumber.api.CucumberOptions;		
import cucumber.api.junit.Cucumber;		

@RunWith(Cucumber.class)				
@CucumberOptions(features="Features",glue={"StepDefinition"},plugin="json:target/jsonReports/cucumber-report.json",tags= {"@AddPlace"})						
public class Runner 				
{		

}