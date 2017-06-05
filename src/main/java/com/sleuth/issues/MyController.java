package com.sleuth.issues;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by panthro on 05/06/2017.
 */
@RestController
@Slf4j
public class MyController {


    private static final String HEADER_DELIMITER = "-";

    private static final String UPPER_CASE = "MY_BAG_UPPER";

    private static final String LOWER_CASE = "my_bag_lower";

    private final Tracer tracer;

    private final MyClient client;


    @Autowired
    public MyController(Tracer tracer, MyClient client) {
        this.tracer = tracer;
        this.client = client;
    }


    @GetMapping("/filled")
    public String call(HttpServletRequest request) {
        String upperCase = request.getHeader(Span.SPAN_BAGGAGE_HEADER_PREFIX + HEADER_DELIMITER + UPPER_CASE);
        String uBaggageItem = tracer.getCurrentSpan().getBaggageItem(UPPER_CASE);
        log.info("Value from baggage: {} - {}", UPPER_CASE, uBaggageItem);
        log.info("Value from HEADER {} - {}", UPPER_CASE, upperCase);

        String lowerCase = request.getHeader(Span.SPAN_BAGGAGE_HEADER_PREFIX + HEADER_DELIMITER + LOWER_CASE);
        String lBaggageItem = tracer.getCurrentSpan().getBaggageItem(LOWER_CASE);
        log.info("Value from baggage: {} - {}", LOWER_CASE, lBaggageItem);
        log.info("Value from HEADER {} - {}", LOWER_CASE, lowerCase);


        return uBaggageItem + ":" + lBaggageItem;
    }


    @GetMapping("/empty")
    public String call() {


        tracer.getCurrentSpan().setBaggageItem(UPPER_CASE, "my_upper_item");
        tracer.getCurrentSpan().setBaggageItem(LOWER_CASE, "my_lower_item");
        String filled = client.filled();
        log.info("RPC returned: {}", filled);


        return filled;
    }
}
