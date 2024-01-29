# My Personal Project

## Gift Card Management System

### what will the application do?
*The application is to help manage the gift card in one account.*

### Who will use it?
*The stores which provide their customers with gift cards and their customers will use this application.*

### Why is this project of interest to me?
*With this application, it will be much easier for people who have several gift cards from one store. 
They can add the gift card to their account when they got a new gift card from a friend. And in this way,
when they want to pay the bill in this store using gift card, they don't need to consider which gift card 
is with the least balance and should be used firstly. This application will help them to do that. This 
application is of interest to me as it contributes a little to bringing our daily life some convenience.*

**User Story**
- As a user, I want to be able to add multiple gift cards to my account.
- As a user, I want to be able to know the total balance of the gift cards in my account.
- As a user, I want to be able to remove a gift card from my account.
- As a user, I want to be able to use the gift card with the least balance firstly when completing a payment.
- As a user, I want to be able to see the list of gift cards in my account.
- As a user, when I select the quit option from the application menu, I want to be reminded to save my gift card list 
to file and have the option to do so or not. 
- As a user, when I start the application, I want to be given the option to load my gift card list from file.

# Instructions for Grader

- You can see the list of "giftCards" in the "account" by clicking "View list" button
- 
- You can add a new "giftCard" to the current "giftCards" list of "account" by clicking "Add a new giftCard" button
- and then type in its name and balance, you can save the "giftCard" you add here to the file 
- by clicking "Finish and Save" button, if you want to save the "giftCard" you add here 
- just to the current "giftCards" list instead of the file you should click "Finish without saving"
- 
- You can remove a "giftCard" from the current "giftCards" list of "account" by clicking "Remove a giftCard" button 
- and then type in its name, you can update the state of the "giftCard" you remove here in the file
- by clicking "Finish and Save" button, if you want to remove the "giftCard" you remove here
- just from the current "giftCards" list instead of the file you should click "Finish without saving" button
- 
- You can pay for a bill using "giftCards" in the "account" by clicking "Pay for a bill" 
- and then type in the amount of bill. If you want to save the change of "giftCards" in the "account" 
- after paying for the bill you should click "Finish and Save" button.
- If you just want to show the change of "giftCards" in the "account" after paying for the bill in the current list
- instead of the file, you should click "Finish without saving" button
- 
- You can reload the state of my application when opening the application
- 
- You can save the state of my application by clicking "Save the current list to the file" button
- 
- You can quit the application after finishing using it by clicking "Quit GiftCard Application" button

## Phase 4: Task 2
Fri Mar 31 14:21:53 PDT 2023
A giftCard named wyy2 with balance 170.0 is added to this account.
Fri Mar 31 14:21:53 PDT 2023
A giftCard named w5 with balance 40.0 is added to this account.
Fri Mar 31 14:22:28 PDT 2023
This giftCard named w5 is removed from this account!
Fri Mar 31 14:22:38 PDT 2023
A giftCard named w1 with balance 20.0 is added to this account.
Fri Mar 31 14:22:44 PDT 2023
A giftCard named w3 with balance 30.0 is added to this account.
Fri Mar 31 14:22:51 PDT 2023
A giftCard named w4 with balance 40.0 is added to this account.
Fri Mar 31 14:22:56 PDT 2023
A giftCard named w5 with balance 50.0 is added to this account.
Fri Mar 31 14:23:11 PDT 2023
The amount of 20.0 in this bill is paid using giftCard w1
Fri Mar 31 14:23:11 PDT 2023
The amount of 30.0 in this bill is paid using giftCard w3
Fri Mar 31 14:23:11 PDT 2023
The amount of 40.0 in this bill is paid using giftCard w4
Fri Mar 31 14:23:11 PDT 2023
The amount of 10.0 in this bill is paid using giftCard w5
Fri Mar 31 14:23:17 PDT 2023
The amount of 30.0 in this bill is paid using giftCard w5
Fri Mar 31 14:23:22 PDT 2023
This giftCard named w5 is removed from this account!

## Phase 4: Task 3
I like my project because I think it can be used in daily life. 
However, if I have more time, I hope I could improve it on the following aspects:
- I will extract the same method in the AddGiftCardView and RemoveGiftCardView class
- and merge these two classes into one
- I will add the function for user to set up the information of the original account instead of using the default one