package com.projectmanagement.cw_dm2_be.api_Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class messageController {

    @GetMapping("/api/message")
    public String message() {
        return "Back end";
    }
}
