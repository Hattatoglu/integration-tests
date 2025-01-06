package xmpl.eyaz.integration.message.restaurant;

import xmpl.eyaz.integration.message.command.CreateOrderCommand;

public interface RestaurantEventPublisher {
    CreateOrderCommand publish(CreateOrderCommand command);
}
