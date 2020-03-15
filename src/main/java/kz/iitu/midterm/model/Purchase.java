package kz.iitu.midterm.model;

import org.springframework.stereotype.Component;

@Component
public class Purchase {
    private int id;
    private int ticketAmount;
    private int total;

    public Purchase() {
    }

    public Purchase(int id, int ticketAmount, int total) {
        this.id = id;
        this.ticketAmount=ticketAmount;
        this.total=total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(int ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Total is " + total;
    }

}
