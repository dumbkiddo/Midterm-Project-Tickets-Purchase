package kz.iitu.midterm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;


    @JsonIgnoreProperties("clients")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
    )
    @JoinTable(
            name="client_tickets",
            joinColumns = @JoinColumn(name="client_id"),
            inverseJoinColumns = @JoinColumn(name="ticket_id")
    )
    private List<Ticket> tickets;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

//    public void addTicket(Ticket ticket) {
//        if (tickets == null)
//            tickets = new ArrayList<>();
//
//        tickets.add(ticket);
//    }
//
//    public void removeTicket(Ticket ticket) {
//        getTickets().remove(ticket);
//        ticket.setClients(null);
//    }
}