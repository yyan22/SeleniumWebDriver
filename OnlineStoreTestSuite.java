package automationFramework;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({  SubmitOrderTestCase.class, UpdateAccountTestCase.class, 
		EmptyCartMsgTestCase.class,})
public class OnlineStoreTestSuite {

}
