# SWEN-732-HW7

### Domain
I chose to implement a course enrollment/drop system to demonstrate the visitor pattern.

The visitor pattern is not something that I have ever used outside of organic contexts. I tried to use the visitor pattern only when dealing with a collection as a whole, as that is how I understand the visitor pattern is typically utilized.

Therefore, when you add/drop a class, you are not using the visitor pattern; instead, it finds the object in the collection and directly calls the relevant methods.

However, when you are printing out a collection or saving the changed state, the visitor pattern is used because it involves a collection of objects.


### Class Diagram
![Class Diagram](src/main/resources/ClassDiagram.png)
### Requirements
I am using Java 17
Maven 15
### How to Run
mvn compile
mvn exec:java

#### Navigating System
``To login to the system type your name:``

To login enter the name of a professor or student located in the json files located: src/main/json
It is case-sensitive and expects both first and last name.

````
Menu:
1. Add course
2. Drop course
3. View courses available
4. Print personal info
5. Save changes
6. logout
````
Navigating the menu is farly strait forward but adding and dropping courses is not

To add/drop courses you will need to know what courses the person you are currently logged in as has currently as well as the ones that are available

To get the courses available chose 3 from the menu and to view what courses you are currently signed up for pick 4

Once you are ready to add or drop a cource type in the cource code.

Now that a change has been made you can save this will change the jsons to reflect the current java objects. If you relaunch the system the change will persist