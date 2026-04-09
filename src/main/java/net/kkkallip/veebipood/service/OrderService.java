package net.kkkallip.veebipood.service;

import lombok.RequiredArgsConstructor;
import net.kkkallip.veebipood.dto.OrderRowDto;
import net.kkkallip.veebipood.entity.Order;
import net.kkkallip.veebipood.entity.OrderRow;
import net.kkkallip.veebipood.entity.Person;
import net.kkkallip.veebipood.entity.Product;
import net.kkkallip.veebipood.repository.OrderRepository;
import net.kkkallip.veebipood.repository.PersonRepository;
import net.kkkallip.veebipood.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    private PersonRepository personRepository;

    private ProductRepository productRepository;

    public Order saveOrder(Long personId, String parcelMachine, List<OrderRowDto> orderRows) {
        Order order = new Order();
        order.setCreated(new Date());
        order.setParcelMachine(parcelMachine);
        //order.setOrderRows(orderRows);
        Person person = personRepository.findById(personId).orElseThrow();
        order.setPerson(person);
        order.setTotal(calculateOrderTotal(orderRows, order));
        return orderRepository.save(order);
    }

    private double calculateOrderTotal(List<OrderRowDto> orderRowDtos, Order order) {
        double total = 0;
        List<OrderRow> orderRows = new ArrayList<>();

        for (OrderRowDto dto : orderRowDtos) {
            Product product = productRepository.findById(dto.productId()).orElseThrow();
            total += dto.quantity() * product.getPrice();

            OrderRow row = new OrderRow();
            row.setProduct(product);
            row.setQuantity(dto.quantity());
            orderRows.add(row);
        }
        order.setOrderRows(orderRows);
        return total;
    }

}
