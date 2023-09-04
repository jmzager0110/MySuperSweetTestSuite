package a11y;

import com.deque.axe.AXE;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Asser;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class sampleTest {

    WebDriver driver;
    private static final URL scriptURL = SampleTest.class.getResource("/axe.min.js");

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup;
        driver = new ChromeDriver();
        driver.get("https://jmzager0110.github.io/jmzager0110-resume/");
    }

    @After
    public void tearDown() {
        driver.quit;
    }

    @Test
    public void sampleA11yTest() {
        JSONObject responseJson = new AXE.Builder(driver, scriptURL).analyze();
        JSONArray violations = responseJson.getJSONArray("violations");

        if(violations.length() == 0) {
            System.out.println("NO ERRORS FOUND!")
        }
        else {
            AXE.writeResults("src/test/a11y/AxeResults", responseJson);
            Assert.assertTrue(AXE.report(violations), false);
        }
    }
}