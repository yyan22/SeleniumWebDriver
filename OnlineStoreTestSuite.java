package automationFramework;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ EmptyCartMsgTestCase.class, SubmitOrderTestCase.class,
		UpdateAccountTestCase.class })
public class OnlineStoreTestSuite {

}
