package automationFramework;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class OnlineStoreTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(OnlineStoreTestSuite.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("Test suite running result: " + result.wasSuccessful());
     }
}  
