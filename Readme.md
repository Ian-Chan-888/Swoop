WestJet Flight booking automation assessment. 

This is a short test script written in Java, using the Selenium framework. 

Assumptions:
1. To run the program one can use an IDE of their choice. I used eclipse but IntelliJ should work as well. 
2. The test is written for the chrome browser using the chromedriver.exe which can be downloaded https://chromedriver.chromium.org/downloads. 
3. This is a standalone script so there is no framework (TestNg, Maven build) supported here. 


Test: 
The following will outline the test functions and then validate the results by printing a Pass or Fail in the Console. 

1. Open Chrome browser and navigate to https://www.westjet.com/en-ca/book-trip/flight
2. Edit the "From" Field using a combination of autocomplete and keypress actions such as Down Arrow and Enter.
	- clear the From Field by clicking on the "X"
	- Expected result = "Vancouver, BC"
3. Edit the "To" Field using autocomplete and multiple key presses such as "Down Arrow" x 2 and Enter.
	- clear the To Field by clicking on the "X"
	- Expected result = "London, United Kingdom
4. Increment the Adults stepper by 1
	- Expected result = 2 Adults
5. Increment the Children stepper by 1
	- Expected result = 1 Children
6. Setting the Departure Month by looking for the expected Month and if it does not find it then clicking on the "Right Arrow" button
	- Expected result = the month of December should be selected.
7. Setting the Departure Date by looking at all the calendar date elements and checking to see if the correct one is visible then clicking on it
	- Expected result = 25 day   *Merry Xmas !!!, or Happy non-denominational day!!! YAY
8. Click on the "Submit" button and Wait
	- Expected result = flight results page will appear. 
9. To check if all the valid values entered were correct I elected to audit the URL which contains the selections chosen. If the known URL matches then this test passes. 


Known Issues:

1. At line 91:
Element reference is not visible. This problem is intermitent, subsequent reruns will pass, but more investigation on 'Stale Elements' are needed.
snip: 
String text=driver.findElements(By.cssSelector("div[class='dw-cal-day-fg']")).get(i).getText();
Console message:
'stale element reference: element is not attached to the page document'


2. The calendar date picker is fragile. 
Because there are several similar elements representing the 'days' and based on the fact that the findElements seems to search the rightmost calendar before the leftmost one. 
There is a large possibility that the incorrect month/day will be chosen. 
To make this more efficent I would recommend making the 'days' elements more unique.



Thanks, 

Ian 
