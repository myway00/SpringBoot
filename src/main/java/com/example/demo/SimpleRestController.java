package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/rest")
public class SimpleRestController {
    private static final Logger logger = LoggerFactory.getLogger(SimpleRestController.class);

    @GetMapping("/simple-payload")
    public SimplePayload simplePayload(){
        return new SimplePayload("custard", 23,"Developer");
    }

    @GetMapping(
            value = "/simple-image",
            produces = MediaType.IMAGE_PNG_VALUE
    )

    public byte[] simpleImage() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/static/img.png");//name에 해당하는 애는 resource 폴더 안에 있을 걸

        return inputStream.readAllBytes();
    }
}
