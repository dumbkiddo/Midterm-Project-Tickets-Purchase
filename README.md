# Final project - Application for booking tickets to movies
* Implemented
  * User Interface with HTML,CSS
  * Swagger2 Documentation

# Description
-- Users functionality : 
1. Login and view movies 
2. Place an order for movie tickets 
3. Edit personal order details 

-- Admin functionality : 
1. Login and view,edit movies 
2. View the orders placed by users 
3. Add and edit new movies

# Pages
1. localhost:8080/ - **Main page** 
2. localhost:8080/login - **Login page** 
3. localhost:8080/movieList - - **List of available movies** 
4. localhost:8080/shoppingCart - **Cart for ordering tickets** 
5. localhost:8080/shoppingCartCustomer - **Adding or edition order information** 
6. localhost:8080/admin/orderList - **View the orders**  - *Only for admins*
7. localhost:8080/admin/movie - **Add or edit movies**  - *Only for admins*

# Project UML diagram with main entities
![Project UML diagram with main entities](https://github.com/dumbkiddo/Midterm-Project-Tickets-Purchase/blob/master/JAVA2%20FINAL.png)

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
 
