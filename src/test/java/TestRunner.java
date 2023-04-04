import org.testng.TestNG;

import java.util.List;

public class TestRunner {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        testng.setTestSuites(List.of("testng.xml"));
        testng.setSuiteThreadPoolSize(3);
        testng.run();
    }
}
