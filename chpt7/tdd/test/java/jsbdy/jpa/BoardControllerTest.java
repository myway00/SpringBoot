package jsbdy.jpa;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BoardController.class)
//없는 보드 컨트롤러를 만들었으니 당연히 빨간줄
//타겟 데스티네이션을 main으로 해서 생성
public class BoardControllerTest {
    @Autowired
    private MockMvc mockMvc;



    @Test
    public void createBoard() throws Exception {
        //(1) given
        BoardDto boardDto = new BoardDto();
        //얘도 똑같이 만들고
        boardDto.setName("notice");
        //setname 해봤는데 boarddto에 없어 그럼 또 추가

        //(2) when

        //여기에 지금 request 요청 임의 만드는 거니깐 requestBody도 추가
        final ResultActions actions = mockMvc.perform(post("/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        //.contentType(boardDto)//boardDto는 스트링 타입이어야지
                        .content(toJson(boardDto))//json의 형태로
                )
                .andDo(print());
        //(3) then
        actions.andExpectAll(
                status().is2xxSuccessful(),
                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                jsonPath("%.name", is("notice"))

        );
    }
//자바 객체를 byte 로 돌려주는 아이 - Gson으로도 가능
    private byte[] toJson(Object object) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}
