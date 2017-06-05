package com.sleuth.issues;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by panthro on 05/06/2017.
 */
@FeignClient(name = "client", url = "http://localhost:8080")
public interface MyClient {

    @RequestMapping(method = RequestMethod.GET, value = "/filled")
    String filled();

}
