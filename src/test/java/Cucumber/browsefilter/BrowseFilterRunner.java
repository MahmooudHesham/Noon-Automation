package Cucumber.browsefilter;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/Cucumber/browsefilter/BrowseFilter.feature",
        glue = {"Cucumber"},
        plugin = {"pretty", "html:report/cucumber-reports.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)

public class BrowseFilterRunner extends AbstractTestNGCucumberTests {
}
