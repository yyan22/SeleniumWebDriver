Developing Environment:

(1) Programming language: Java.

(2) IDE: Eclipse with Selenium WebDriver library and Json library set up properly.

(3) Browser: Firefox.

**************************************************************************************************************************************

Automation Exercise 1 contains six classes under the package named automationFramework.

(1) OnlineStorePage.java:
This is the basic class for page objects pattern.

(2) SubmitOrderTestCase.java:
Submit an order for an Apple iPhone4s 16GB SIM-Free - Black (known issue with State drop-down, selecting Country is adequate) and verify the Total Price: given is correct (assume shipping cost is correct based on your choice). You may assume prices shown on product pages are the correct price. 

(3) UpdateAccountTestCase.java:
Verify updating your account details is saved and retrieved after logging out and back in using the My Account link. 

(4) EmptyCartMsgTestCase.java:
Verify removing all items from your cart produces an empty cart message.

(5) OnlineStoreTestSuite.java:
The test suite class contains three files to be tested: SubmitOrderTestCase.java, UpdateAccountTestCase.java, EmptyCartMsgTestCase.java.

(6) OnlineStoreTestRunner.java:
This file is used to run the test suite. 

If the test suite is successfully run, the console window should print out three messages:
Apple iPhone4s 16GB SIM-Free - Black order has been successfully placed.
Account details are saved and retrieved.
Removing all items from your cart produces an empty cart message.

Issue noticed:

The State drop-down is not working either when placing an order or updating account details.

**************************************************************************************************************************************

Automation Excecise 2 contains one file:

NearestStationTestCase.java

It performs two tasks:

(1) Query for nearest stations to Austin, TX that are part of the "ChargePoint Network". Verify that "HYATT AUSTIN" appears in the
results. Store/save the Station Id of the HYATT AUSTIN station.

(2) Use the Station ID from previous test to query the API and return the Street Address of that station. Verify the Station Address is 208 Barton Springs, Austin, Texas, USA, 78704.

If the test suite is successfully run, the console window should print out two messages:
"HYATT AUSTIN" appears in the results.
the station address is 208 Barton Springs, Austn, TX 78704.

**************************************************************************************************************************************

If you have any questions, please send email to yanyyj@gmail.com. Thank you!
