package a11y;




public class sampleTest {

    WebDriver driver;
    private static final URL scriptURL = SampleTest.class.getResource("/axe.min.js");

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup;
        driver = new ChromeDriver();
        driver.get("https://whats-the-url-again");
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