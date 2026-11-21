# Lab 01 – Khởi tạo và giao tiếp giữa các Microservices

```
lab01-microservices/
├── product-service/   (cổng 8081)
├── order-service/     (cổng 8082)
└── frontend/
    └── index.html
```

## Chạy hai service (2 terminal riêng)
```
cd product-service
mvn spring-boot:run
```
```
cd order-service
mvn spring-boot:run
```

## Kiểm thử
- http://localhost:8081/api/products
- http://localhost:8081/api/products/1
- http://localhost:8082/api/orders
- http://localhost:8082/api/orders/product/1  (kết quả: {"id":1,"name":"Mouse","price":150000})

Mở `frontend/index.html` bằng Live Server để thử giao diện.

## Git / GitHub (Câu E)
```
git init
git add .
git commit -m "Lab01: Create Product Service and Order Service"
git branch -M main
git remote add origin https://github.com/USERNAME/lab01-microservices.git
git push -u origin main
```
Sau khi làm xong frontend:
```
git status
git add .
git commit -m "Lab01 - Add Frontend and REST API integration"
git push origin main
```
