package xmpl.eyaz.integration.message.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xmpl.eyaz.integration.message.command.CreateOrderCommand;
import xmpl.eyaz.integration.message.dto.CreateOrderRequest;
import xmpl.eyaz.integration.message.dto.CreateOrderResponse;
import xmpl.eyaz.integration.message.service.OrderService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        CreateOrderCommand command = CreateOrderCommand.builder()
                .username(request.getUsername())
                .orderItemId(request.getOrderItemId())
                .price(request.getPrice())
                .restaurantId(request.getRestaurantId())
                .address(request.getAddress())
                .orderStatus("PROCESSING")
                .build();

        CreateOrderCommand answer = orderService.createOrder(command);

        CreateOrderResponse response = new CreateOrderResponse();
        response.setUsername(answer.getUsername());
        response.setOrderCreationTime(command.getOrderCreationTime().toString());
        response.setTrackingId(answer.getTrackingId());
        response.setOrderStatus(answer.getOrderStatus());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
