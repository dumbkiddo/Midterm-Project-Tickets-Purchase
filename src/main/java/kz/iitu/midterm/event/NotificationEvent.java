package kz.iitu.midterm.event;

import kz.iitu.midterm.dao.PurchaseDAO;
import org.springframework.context.ApplicationEvent;

public class NotificationEvent extends ApplicationEvent {

    private PurchaseDAO purchase;

    public NotificationEvent(Object source, PurchaseDAO purchase) {
        super(source);
        this.purchase = purchase;
    }

    public PurchaseDAO getPurchase() {
        return purchase;
    }
}
