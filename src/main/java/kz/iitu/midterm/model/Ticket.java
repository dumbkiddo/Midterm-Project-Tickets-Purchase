package kz.iitu.midterm.model;

import org.springframework.stereotype.Component;

@Component
public class Ticket {
    private int id;
    private String type;
    private int morningPrice;
    private int afternoonPrice;
    private int eveningPrice;

    public Ticket() {
    }

    public Ticket(int id, String type, int morningPrice, int afternoonPrice, int eveningPrice) {
        this.id = id;
        this.type = type;
        this.morningPrice = morningPrice;
        this.afternoonPrice = afternoonPrice;
        this.eveningPrice = eveningPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMorningPrice() {
        return morningPrice;
    }

    public void setMorningPrice(int morningPrice) {
        this.morningPrice = morningPrice;
    }

    public int getAfternoonPrice() {
        return afternoonPrice;
    }

    public void setAfternoonPrice(int afternoonPrice) {
        this.afternoonPrice = afternoonPrice;
    }

    public int getEveningPrice() {
        return eveningPrice;
    }

    public void setEveningPrice(int eveningPrice) {
        this.eveningPrice = eveningPrice;
    }

    @Override
    public String toString() {
        return type;
    }

}
