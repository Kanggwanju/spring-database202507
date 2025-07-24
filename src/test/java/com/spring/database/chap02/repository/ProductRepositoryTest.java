package com.spring.database.chap02.repository;

import com.spring.database.chap01.entity.Book;
import com.spring.database.chap02.dto.PriceInfo;
import com.spring.database.chap02.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("스프링 JDBC로 상품을 생성한다.")
    void saveTest() {
        //given
        Product newProduct = Product.builder()
                .name("냉장고")
                .price(300000)
                .stockQuantity(7)
                .description("자취방에 들어갈 미니 냉장고")
                .seller("뽀로로")
                .status("ACTIVE")
                .build();
        //when
        boolean flag = productRepository.save(newProduct);
        //then
        assertTrue(flag);
    }
    
    @Test
    @DisplayName("스프링 JDBC로 상품 정보를 수정한다.")
    void updateTest() {
        //given
        Product newProduct = Product.builder()
                .id(3L)
                .name("냉장고")
                .price(200000)
                .stockQuantity(7)
                .description("자취방에 들어갈 미니 냉장고")
                .seller("뽀로로")
                .createdAt(LocalDateTime.now())
                .build();
        //when
        boolean flag = productRepository.update(newProduct);
        //then
        assertTrue(flag);
    }
    
    @Test
    @DisplayName("스프링 JDBC로 상품 정보를 논리적으로 삭제한다.")
    void deleteTest() {
        //given
        Long givenId = 2L;
        //when
        boolean flag = productRepository.deleteById(givenId);
        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("스프링 JDBC로 전체조회를 할 경우 상품 목록이 반환된다.")
    void findAllTest() {
        //given

        //when
        List<Product> productList = productRepository.findAll();
        //then
        productList.forEach(System.out::println);
        assertEquals(3, productList.size());
    }
    
    @Test
    @DisplayName("적합한 id를 통해 개별조회를 하면 상품 1개의 객체가 반환된다.")
    void findOneTest() {
        //given
        Long givenId = 1L;
        //when
        Product foundProduct = productRepository.findOne(givenId);
        //then
        System.out.println("foundProduct = " + foundProduct);

        assertNotNull(foundProduct);
        assertEquals("마우스", foundProduct.getName());
    }
    

    @Test
    @DisplayName("총액과 평균")
    void sumAvgTest() {
        //given

        //when
//        Map<String, Object> priceInfo = productRepository.getPriceInfo();
//        for (String s : priceInfo.keySet()) {
//            System.out.println("priceInfo.get(s) = " + priceInfo.get(s));
//        }

        PriceInfo priceInfo = productRepository.getPriceInfo();
        System.out.println("totalPrice = " + priceInfo.getTotalPrice());
        System.out.println("averagePrice = " + priceInfo.getAveragePrice());

        //then
    }

}