package com.mag.trader.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/configuration")
public class ConfigurationApi {

    @GetMapping
    public List<String> list() {
        return Collections.emptyList();
    }
}
