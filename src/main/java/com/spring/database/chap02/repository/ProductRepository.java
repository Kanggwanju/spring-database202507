package com.spring.database.chap02.repository;

import com.spring.database.chap02.dto.PriceInfo;
import com.spring.database.chap02.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final JdbcTemplate template;

    // 상품 추가
    public boolean save(Product product) {
        String sql = """
                    INSERT INTO PRODUCTS
                        (name, price, stock_quantity, description, seller, status, created_at)
                    VALUES
                        (?, ?, ?, ?, ?, ?, ?)
                """;

        return template.update(
                sql,
                product.getName(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getDescription(),
                product.getSeller(),
                product.getStatus(),
                LocalDateTime.now()
        ) == 1;
    }

    // 상품 정보 수정
    public boolean update(Product product) {
        String sql = """
                    UPDATE PRODUCTS
                    SET
                        name = ?,
                        price = ?,
                        stock_quantity = ?,
                        description = ?,
                        seller = ?,
                        status = ?,
                        created_at = ?
                    WHERE id = ?
                """;

        return template.update(
                sql,
                product.getName(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getDescription(),
                product.getSeller(),
                product.getCreatedAt(),
                product.getId()
        ) == 1;
    }
    
    // 상품 정보 논리적 삭제
    public boolean deleteById(Long id) {
        String sql = """
                    UPDATE PRODUCTS
                    SET status = ?
                    WHERE id = ?
                """;

        return template.update(
                sql,
                "DELETED",
                id
        ) == 1;
    }

    // 상품 목록 조회
    public List<Product> findAll() {
        String sql = """
                    SELECT * FROM PRODUCTS
                    WHERE status <> 'DELETED'
                """;


        // BeanPropertyRowMapper: 테이블의 컬럼명과 엔터티클래스의 필드명이
        // 똑같을 경우 (camel, snake 차이만 빼고) 자동 매핑해줌
        return template.query(
                sql,
                new BeanPropertyRowMapper<>(Product.class)
        );
    }

    // 상품 개별 조회
    public Product findOne(Long id) {
        String sql = """
                    SELECT * FROM PRODUCTS
                    WHERE id = ?
                """;

        return template.queryForObject(
                sql,
                (rs, rowNum) -> new Product(rs),
                id
        );
    }

    // 전체 상품의 총액과 평균 가격을 가져오는 기능
    public PriceInfo getPriceInfo() {
        String sql = """
                SELECT 
                    SUM(price * stock_quantity) AS "total_price"
                    , AVG(price * stock_quantity) AS "average_price"
                FROM PRODUCTS
                WHERE status = 'ACTIVE'
                """;

        return template.queryForObject(sql, (rs, rowNum) -> {
            int totalPrice = rs.getInt("total_price");
            double averagePrice = rs.getDouble("average_price");
            
            // dto로 리턴
            return new PriceInfo(totalPrice, averagePrice);

            // Map으로 리턴하기
//            return Map.of(
//                    "total", totalPrice,
//                    "average", averagePrice
//            );
        });

        // BeanPropertyRowMapper, dto 사용
        // return template.queryForObject(sql, new BeanPropertyRowMapper<>(PriceInfo.class));
    }

}
