package jsbdy.jpa;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("board")
@RestController
public class BoardController {
    @PostMapping
    public void createBoard(@RequestBody BoardDto){

    }
}
