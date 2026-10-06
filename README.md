# 🎟️ Ticket Booking Platform

Nền tảng đặt vé sự kiện trực tuyến.

## Công nghệ

| Phần     | Stack                                                            |
| -------- | ---------------------------------------------------------------- |
| Backend  | Java 21, Spring Boot 4, Spring Security, Spring Data JPA, Flyway |
| Database | PostgreSQL 17 (AWS RDS)                                          |
| Frontend | React + TypeScript + Vite (đang phát triển)                      |
| Test     | JUnit 5, Testcontainers                                          |

## Cấu trúc thư mục

```
be/base/          Backend Spring Boot
fe/               Frontend React
infrastructure/   Docker Compose, hạ tầng
docs/             Tài liệu
load-test/        Kịch bản load test
```

## Chạy Backend local

1. Cài Java 21 và PostgreSQL (hoặc dùng AWS RDS của team)
2. Copy file cấu hình:
   ```bash
   cd be/base/src/main/resources
   cp application-local.properties.example application-local.properties
   ```
3. Điền thông tin DB vào `application-local.properties`
4. Chạy:
   ```bash
   cd be/base
   ./mvnw spring-boot:run
   ```

## Quy ước Git

- `main`: code ổn định (được bảo vệ, chỉ merge qua PR)
- `develop`: nhánh phát triển chính
- `feature/<tên>`: mỗi tính năng một nhánh, tạo từ `develop`
- Commit message: `feat:`, `fix:`, `chore:`, `docs:`, `refactor:`, `test:`
