package xmpl.eyaz.integration.message.service;

import org.springframework.stereotype.Service;
import xmpl.eyaz.integration.message.command.CreateOrderCommand;
import xmpl.eyaz.integration.message.restaurant.RestaurantEventPublisher;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
public class OrderServiceCommandHandler implements OrderService{

    private final RestaurantEventPublisher restaurantEventPublisher;

    public OrderServiceCommandHandler(RestaurantEventPublisher restaurantEventPublisher) {
        this.restaurantEventPublisher = restaurantEventPublisher;
    }

    @Override
    public CreateOrderCommand createOrder(CreateOrderCommand command) {
        // service-payment process response :
        command.setOrderStatus("PAID");

        // service-order process :
        command.setTrackingId(UUID.randomUUID().toString());
        command.setOrderCreationTime(ZonedDateTime.now(ZoneOffset.UTC));


        // service-restaurant process :
        CreateOrderCommand answer = restaurantEventPublisher.publish(command);

        if (!answer.getOrderStatus().equals("PENDING")) {
            // Do sth like rollback or retry
        }

        return answer;
    }
}
