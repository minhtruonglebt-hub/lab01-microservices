package com.example.productservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    // Danh sách dữ liệu mẫu (tối thiểu 3 sản phẩm) bằng List.of() và Map.of()
    private final List<Map<String, Object>> products = List.of(
            Map.of("id", 1, "name", "Mouse", "price", 150000),
            Map.of("id", 2, "name", "Keyboard", "price", 250000),
            Map.of("id", 3, "name", "Monitor", "price", 2500000)
    );

    @GetMapping
    public List<Map<String, Object>> getProducts() {
        return products;
    }

    // Tìm theo ID: tìm thấy -> 200 OK, không thấy -> 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProduct(
            @PathVariable int id) {
        return products.stream()
                .filter(p -> p.get("id").equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
