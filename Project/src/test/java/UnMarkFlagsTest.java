
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "features/UnMarkMessagesWithFlags.feature")
public class UnMarkFlagsTest extends AbstractTestNGCucumberTests {}