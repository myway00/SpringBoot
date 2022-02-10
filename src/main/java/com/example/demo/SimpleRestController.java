package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public SimplePayload simplePayloadGet(){
        return new SimplePayload("custard", 23,"Developer");
    }

    @PostMapping("/simple-payload")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String simplePayloadPost(@RequestBody SimplePayload simplepayloadata){
        logger.info(simplepayloadata.toString());
        return simplepayloadata.getName();
    }

    @PostMapping(
            value = "/simple-multipart",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void simpleMultipartpost(
        @RequestParam("name") String name,
        @RequestParam("age") Integer age,
        @RequestParam("occupation") String occupation,
        @RequestParam("file") MultipartFile multipartFile
    ){
        logger.info("name :"+ name);
        logger.info("age :"+ age);
        logger.info("o :"+ occupation);
        logger.info("file name :"+ multipartFile.getOriginalFilename());
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
