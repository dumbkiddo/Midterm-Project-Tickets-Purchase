Final project - Application for booking tickets to movies
-- User Interface with HTML,CSS
-- Swagger2 Documentation
-- Users functionality : 1) Login and view movies 2) Place an order for movie tickets 3) Edit personal order details 
-- Admin functionality : 1) Login and view,edit movies 2) View the orders placed by users 3) Add new movies

----------------------------------------------------------------------

# Endterm description
-- Spring Data JPA
-- REST Controllers 
- Movie,Ticket,Purchase and Client
-- Security Authorization
- Authorize as admin
Admin can add,edit delete data
- Authorize as user
User can view records
-- Lombok and Swagger2
-- Exception handling

----------------------------------------------------------------------
# Midterm Description 
Midterm project with Spring - Cinema system
 This project implements a system for purchasing tickets to cinema.
 
 - Select a movie from the list fetched from database 
 - Select a time of movie showing - morning / afternoon / evening
 - Enter the number of tickets to purchase
 - Enter the type of each ticket - adult / student / child
 - Receive a notification with purchase information - number of purchased tickets and total price
 
 Beans
 
- MovieDAO - stores queries for outputting a list of movies and their schedule
- TicketDAO - stores queries for ticket information (types,prices)
- PurchaseDAO - stores information on purchase (number of tickets,total price)
- PurchaseCalculator - calculates the total purchase

 SpringConfiguration used for connecting to database.
 NotificationEvent and handler used for outputting notification with purchase information.
 SQL file is provide in recources folder.
 
