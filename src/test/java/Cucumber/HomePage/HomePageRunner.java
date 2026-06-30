package Cucumber.HomePage;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/Cucumber/HomePage/validHomePage.feature",
        glue = {"Cucumber"},
        plugin = {"pretty", "html:report/cucumber-reports.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)
public class HomePageRunner extends AbstractTestNGCucumberTests {
}
