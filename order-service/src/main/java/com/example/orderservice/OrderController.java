package com.example.orderservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

// Frontend (Live Server) gọi trực tiếp Order Service nên cần cho phép CORS
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final RestClient restClient;

    public OrderController(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://localhost:8081")
                .build();
    }

    @GetMapping
    public List<Map<String, Object>> getOrders() {
        return List.of(
                Map.of("id", 101, "productId", 1, "quantity", 2),
                Map.of("id", 102, "productId", 2, "quantity", 1)
        );
    }

    // Gọi Product Service bằng RestClient
    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable int id) {
        try {
            return restClient.get()
                    .uri("/api/products/{id}", id)
                    .retrieve()
                    .body(String.class);
        } catch (HttpClientErrorException.NotFound e) {
            // Product Service trả 404 -> chuyển tiếp 404 để Frontend hiển thị "Không tìm thấy sản phẩm"
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
