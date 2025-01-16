package com.works.services;

import com.works.clients.ProductClient;
import com.works.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService {

    // final DiscoveryClient discoveryClient;
    final ProductClient productClient;
    final CircuitBreakerFactory circuitBreakerFactory;

    public Product singleProduct( Long pid  ) {
        /*
        List<ServiceInstance> instances = discoveryClient.getInstances("Product");
        if ( instances != null && !instances.isEmpty() ) {
            ServiceInstance instance = instances.get(0);
            String uri = instance.getUri().toString();
            RestTemplate restTemplate = new RestTemplate();
            uri = uri + "/product/getOne/"+ pid;
            ResponseEntity<Product> response = restTemplate.getForEntity(uri, Product.class);
            return response.getBody();
        }
        return null;
         */
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("productBreaker");
        return circuitBreaker.run(
                () -> productClient.getOne(pid),
                throwable -> fallBack(pid)
        );
    }

    public Product fallBack( Long pid ) {
        return null;
    }

}
