package com.works.restcontrollers;

import com.works.models.Product;
import com.works.services.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("basket")
public class BasketRestController {

    final BasketService basketService;

    @GetMapping("single/{pid}")
    public ResponseEntity<Product> single(@PathVariable Long pid ) {
        Product pro = basketService.singleProduct(pid);
        if (pro.getDescription() == null) {
            return new ResponseEntity<>(pro, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().body(pro);
    }

}
