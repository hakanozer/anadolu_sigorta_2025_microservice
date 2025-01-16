package com.works.clients;

import com.works.models.PostModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "jsonbulut", url = "https://jsonbulut.com/api/")
public interface BulutClient {

    @GetMapping("posts/{pid}")
    PostModel getPost(@PathVariable Long pid);

}
