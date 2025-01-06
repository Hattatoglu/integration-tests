package xmpl.eyaz.integration.message.service;

import xmpl.eyaz.integration.message.command.CreateOrderCommand;

public interface OrderService {
    CreateOrderCommand createOrder(CreateOrderCommand command);
}
