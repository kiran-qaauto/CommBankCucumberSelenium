package runnerFiles;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/featureFiles/interestCalculator.feature",
        glue = "stepDef",
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" ,
                "pretty", "html:target/cucumber-reports/cucumber_reports.html"}
)
public class InterestCalculatorRunner {
}
