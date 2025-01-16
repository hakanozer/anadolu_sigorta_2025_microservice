package com.works.clients;

import com.works.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

// value = "product" -> service name
@FeignClient(value = "product", path = "product")
public interface ProductClient {

    @GetMapping("getOn/{pid}")
    Product getOne(@PathVariable Long pid);

    @PostMapping("save")
    Product save(@RequestBody Product product);

}
