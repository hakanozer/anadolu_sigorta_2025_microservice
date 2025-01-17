package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import jakarta.jms.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository productRepository;
    final JmsTemplate jmsTemplate;

    public Product save( Product product ) {
        jmsTemplate.convertAndSend("q1", product);
        return product;
    }

    // listener
    @JmsListener(containerFactory = "productContainer", destination = "q1")
    public void productListener(Product product, Message message) throws Exception {
        System.out.println("Product Save - " + message.getJMSMessageID() );
        productRepository.save(product);
        try {
            Thread.sleep(500);
        }catch (Exception ex){

        }
    }

    public List<Product> list() {
        return productRepository.findAll();
    }

    public Product getOne(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return productOptional.get();
        }
        return null;
    }

}
