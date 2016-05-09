Testing Environment:

1. Programming language: Java.

2. IDE: Eclipse with Selenium WebDriver set up properly.

3. Browser: Firefox.

Automation Exercise 1 contains 4 files: 

All these 4 classes are from the same package named automationFramework.

(1) OnlineStorePage.java:
This is the basic class for page objects pattern.

(2) SubmitOrderTestCase.java:
Submit an order for an Apple iPhone4s 16GB SIM-Free - Black (known issue with State drop-down, selecting Country is adequate) and verify the Total Price: given is correct (assume shipping cost is correct based on your choice). You may assume prices shown on product pages are the correct price. 

(3) UpdateAccountTestCase.java:
Verify updating your account details is saved and retrieved after logging out and back in using the My Account link. 

Please note: This test case has not been implemented. The reason it is not implemented is because I registered online but have not received any confirmation email. As long as I get the link to login, I will get to this test case immediately.

(4) EmptyCartMsgTestCase.java:
Verify removing all items from your cart produces an empty cart message.

Automation Excecise 2 contains 1 file:

NearestStationTestCase.java

It performs 2 tasks:

(1) Query for nearest stations to Austin, TX that are part of the "ChargePoint Network". Verify that "HYATT AUSTIN" appears in the
results. Store/save the Station Id of the HYATT AUSTIN station.

(2) Use the Station ID from previous test to query the API and return the Street Address of that station. Verify the Station Address is 208 Barton Springs, Austin, Texas, USA, 78704.

If you have any questions, please email yanyyj@gmail.com or call 512-952-9800. Thank you!
