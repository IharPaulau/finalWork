package utils;


import org.springframework.beans.factory.annotation.Autowired;
import service.OrderService;

public class StateChangerRunnable implements Runnable {

    @Autowired
    private OrderService orderService;

    /**
     *
     */
    @Override
    public void run() {
        do {
            orderService.cancelExpiredOrders();
            orderService.autoChangeOrderStatusToReturn();
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
