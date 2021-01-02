# Calculator_App
The goal of this project was a simple calculator app, with two different layouts.<br/>
This was the first project I created in Android Sutdio and I used a course from [Udemy](https://www.udemy.com/) to get me into it.

### About the project
The calculator can perform simple operations like addition, subtraction, multiplication and division. More functionality will be added in the future.<br/>
The standard layout and the landscape layout are created seperatly, to fit better to the screen.<br/>
When switching between layouts, all entered variables get saved and restored, so you don't have to type them in again.

### Classes and methods
The whole functionality was implemented in the MainActivity class, making it not very well-arranged to be honest. This is something, my future projects will be better at. <br/>
Three instance variables store pretty much all input for the App. <br/>
The instance variables newNumber and Result are pretty much self-explaining, they store new input and the displayed result. <br/>
The instance variable displayOperation holds the Operation, that is used for the next calculation. <br/>
Four different onClickListener are created, for the number-buttons, the operation-buttons, the negative-button and the clear-button. <br/>
In the method performOperation the operations are executed, according to the button clicked. 


### Learnings
* Constraining buttons and other widgets to look good on screen
* Saving and restoring data between activities
* Understanding the activity-lifecycle
* Creating different layouts for different formats

