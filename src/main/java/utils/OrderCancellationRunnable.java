package utils;


import org.springframework.beans.factory.annotation.Autowired;
import service.OrderService;

public class OrderCancellationRunnable implements Runnable {

    @Autowired
    private OrderService orderService;

    @Override
    public void run() {
        do {
            orderService.cancelExpiredOrders();
            try {
                Thread.sleep(20000); //20 sec
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
