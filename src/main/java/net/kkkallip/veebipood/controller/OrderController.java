package net.kkkallip.veebipood.controller;

import lombok.RequiredArgsConstructor;
import net.kkkallip.veebipood.dto.OrderRowDto;
import net.kkkallip.veebipood.entity.Order;
import net.kkkallip.veebipood.entity.OrderRow;
import net.kkkallip.veebipood.entity.Product;
import net.kkkallip.veebipood.repository.OrderRepository;
import net.kkkallip.veebipood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private OrderRepository orderRepository;

    private OrderService orderService;

    @GetMapping("orders")
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @DeleteMapping("orders/{id}")
    public List<Order> deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
        return orderRepository.findAll();
    }

    @PostMapping("orders")
    public Order addOrder(@RequestParam Long personId, @RequestParam(required = false) String parcelMachine, @RequestBody List<OrderRowDto> orderRows) {
        return orderService.saveOrder(personId, parcelMachine, orderRows);
    }
}
