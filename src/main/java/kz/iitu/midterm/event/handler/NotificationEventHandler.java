package kz.iitu.midterm.event.handler;

import kz.iitu.midterm.event.NotificationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventHandler implements ApplicationListener<NotificationEvent> {

    @Override
    public void onApplicationEvent(NotificationEvent notifyEvent) {
        System.out.println("-----Notification-----\nPurchase is confirmed.\nAmount of purchased tickets: " + notifyEvent.getPurchase().getPurchaseById(1).getTicketAmount() + "\nTotal price: " + notifyEvent.getPurchase().getPurchaseById(1).getTotal());
    }
}
