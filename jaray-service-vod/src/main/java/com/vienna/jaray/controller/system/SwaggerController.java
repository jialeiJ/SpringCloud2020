package com.vienna.jaray.controller.system;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jaray
 * @date 2020年09月10日 22:29
 * @description: swagger制器
 */
@CrossOrigin
@RestController
public class SwaggerController {
    @GetMapping("/swagger")
    public String index() {
        return "swagger2";
    }
}
