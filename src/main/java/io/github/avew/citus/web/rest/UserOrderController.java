package io.github.avew.citus.web.rest;

import io.github.avew.citus.domain.UserOrder;
import io.github.avew.citus.repository.UserOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class UserOrderController {

    private final UserOrderRepository repo;

    @PostMapping
    public UserOrder create(@RequestBody UserOrder o) {
        return repo.save(o);
    }

    @GetMapping
    public List<UserOrder> all() {
        return repo.findAll();
    }

    @GetMapping("/with-user")
    public List<Object[]> allWithUser() {
        return repo.findOrdersWithUser();
    }
}