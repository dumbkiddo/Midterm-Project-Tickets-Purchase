# Midterm project with Spring - Cinema system
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
 
