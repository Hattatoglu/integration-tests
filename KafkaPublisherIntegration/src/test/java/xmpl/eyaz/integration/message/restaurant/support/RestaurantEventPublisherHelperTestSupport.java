package xmpl.eyaz.integration.message.restaurant.support;

import xmpl.eyaz.integration.message.command.CreateOrderCommand;

import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;


public class RestaurantEventPublisherHelperTestSupport {

    protected CreateOrderCommand getCreateOrderCommand() {
        CreateOrderCommand command = CreateOrderCommand.builder()
                .username("test-username")
                .orderItemId("test-orderItemId-1234")
                .price(BigDecimal.valueOf(123.45))
                .restaurantId("test-restaurantId")
                .address("test-address")
                .orderStatus("PAID")
                .build();

        command.setOrderCreationTime(ZonedDateTime.now(ZoneOffset.UTC));
        command.setTrackingId(UUID.randomUUID().toString());

        return command;
    }

}
