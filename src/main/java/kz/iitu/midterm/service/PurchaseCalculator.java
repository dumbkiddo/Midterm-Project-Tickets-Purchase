package kz.iitu.midterm.service;

import kz.iitu.midterm.dao.TicketDAO;
import kz.iitu.midterm.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class PurchaseCalculator {

    Ticket ticket;
    public PurchaseCalculator(Ticket ticket) {
        this.ticket = ticket;
    }

    //Calculate the price for ticket based on the type and time of the day
    public int calculatePrice(TicketDAO ticket, int ticketChoice, int timeChoice){
        int price;
        int totalPrice = 0;
        //morning prices
        if (timeChoice == 1) {
            //adult
            if (ticketChoice==1) {
                price = ticket.getTicketById(1).getMorningPrice();
                totalPrice += price;
            }
            //student
            if (ticketChoice==2) {
                price = ticket.getTicketById(2).getMorningPrice();
                totalPrice += price;
            }
            //child
            if (ticketChoice==3) {
                price = ticket.getTicketById(3).getMorningPrice();
                totalPrice += price;
            }
        }
        //afternoon prices
        if (timeChoice == 2) {
            //adult
            if (ticketChoice==1) {
                price = ticket.getTicketById(1).getAfternoonPrice();
                totalPrice += price;
            }
            //student
            if (ticketChoice==2) {
                price = ticket.getTicketById(2).getAfternoonPrice();
                totalPrice += price;
            }
            //child
            if (ticketChoice==3) {
                price = ticket.getTicketById(3).getAfternoonPrice();
                totalPrice += price;
            }
        }
        //evening prices
        if (timeChoice == 3) {
            //adult
            if (ticketChoice==1) {
                price = ticket.getTicketById(1).getEveningPrice();
                totalPrice += price;
            }
            //student
            if (ticketChoice==2) {
                price = ticket.getTicketById(2).getEveningPrice();
                totalPrice += price;
            }
            //child
            if (ticketChoice==3) {
                price = ticket.getTicketById(3).getEveningPrice();
                totalPrice += price;
            }
        }
        return totalPrice;
    }

    public String toString() {
        return this.ticket.toString();
    }
}
