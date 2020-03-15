package kz.iitu.midterm;

import kz.iitu.midterm.configuration.SpringConfiguration;
import kz.iitu.midterm.dao.MovieDAO;
import kz.iitu.midterm.dao.PurchaseDAO;
import kz.iitu.midterm.dao.TicketDAO;
import kz.iitu.midterm.model.Movie;
import kz.iitu.midterm.service.PurchaseCalculator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        Scanner scan = new Scanner(System.in);

        //Beans
        MovieDAO movie = context.getBean(MovieDAO.class);
        TicketDAO ticket = context.getBean(TicketDAO.class);
        PurchaseCalculator cs = context.getBean(PurchaseCalculator.class);
        PurchaseDAO purchase= context.getBean(PurchaseDAO.class);
        int totalPrice = 0;

        //List all movies
        for (Movie e : movie.getAllMovies()) {
            System.out.println(e);
        }

        System.out.println("Choose a movie from the list: ");
        int choice = scan.nextInt();

        //List schedule for chosen movie
        System.out.println(movie.getMovieById(choice).seeTime());
        System.out.println("Choose the time: ");
        int timeChoice = scan.nextInt();

        //Enter amount of tickets
        System.out.println("Enter number of tickets: ");
        int ticketNumber = scan.nextInt();
        final int ticketNum = ticketNumber;

        //Enter types of tickets
        while (ticketNumber>0) {
            System.out.println("Choose ticket type:\n1.Adult\n2.Student\n3.Child");
            int ticketChoice = scan.nextInt();
            //Calculate total
            totalPrice += cs.calculatePrice(ticket, ticketChoice,timeChoice);
            ticketNumber--;
        }
        //Notification with purchase info
        purchase.update(purchase,1, ticketNum, totalPrice);
        context.close();
    }
}
