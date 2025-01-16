package com.works.restcontrollers;

import com.works.clients.BulutClient;
import com.works.models.PostModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("basket")
public class BulutRestController {

    final BulutClient bulutClient;

    @GetMapping("post/{pid}")
    public PostModel post(@PathVariable Long pid) {
        return bulutClient.getPost(pid);
    }

}
