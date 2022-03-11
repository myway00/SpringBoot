package jsbdy.jpa;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
//Controller, ControllerAdvice 만 ㄱㄴ
//테스트 사용할 때 지정된 애만 테스트하기위한 어노테이션
//컨트롤러 요청에 들어오는 애들 테스트 하기위함이다 명시
class PostControllerTest {
    //서비스같은애들은 따로 빈객체로 안만들어져서
    // 이렇게 서비스따로 자동와이아해줘도 빈안만드러져
//    @Autowired
//    private PostService postService;
    //그래서 서비스인"척"해줄 아이를 mock으로 만들거야
    @Autowired
    private MockMvc mockMvc;
    //HTTP클라이언트인척하는애
    //perform이라는 메소드로 http에게 해당
    //url요청을 보낸 것 같이 행동

    @MockBean
    //실제로 만들어지지 않은 빈을 임의로 만든 것
    //
    private PostService postService;

    @Test
    void readPost() throws Exception{//perfoem이 예외 던질 것 대비해서
        //3단계로 테스트 케이스
        //1단계 : given : 어떤 데이터가 준비 돼있다
        //PostEntity가 존재할 때(PostService가 PostEntity를 잘 돌려줄 때)
        final int id = 10;
        PostDto testDto = new PostDto();
        testDto.setId(10);
        testDto.setTitle("Unit Title");
        testDto.setContent("Unit Content");
        testDto.setWriter("unit");

        //위의 아이는 아무기능도 가지지 않음 - 역할 부여 해야함
        //mockito.BDDMockito 에서 기능 부여해줄 given 사용
        //postService가 이 메소드를 수행하면 일어날 일을 정의해주는 것
        given(postService.readPost(id)).willReturn(testDto);

        //2단계 : when : 어떠한 행위가 일어났을 때(함수호출 등)
        //경로에 GET 요청이 온다면
        final ResultActions actions = mockMvc.perform(get("/post/10"))
                .andDo(print());
        //actions라는 아이에게는 저러한 결과가 담기게 된다

        //3단계 : then : 어떤 결과가 올 것인지
        //PostDto가 반환된다
        actions.andExpectAll(
                status().isOk(),//status가 200인지
                content().contentType(MediaType.APPLICATION_JSON),
                //돌아온 값이 json인지 확인 & 아래는 json을 위한 정규표현식
                //$는 제이슨 한 문서를 의미 - 그 안에 title의 키 값이 is에 들가는 건지
                jsonPath("$.title", is("Unit Title")),
                jsonPath("$.content",is("Unit Content")),
                jsonPath("$.writer", is("unit"))

        );
        //actions에 정의된 함수들이 andExpectAll을 통해 출력됨
    }

    @Test
    void readPostAll() throws Exception{
        //given : 어떤 데이터가 주어질 것이다
        //given으로 할 때 given(내가테스트할함수).willreturn(내가 지정한 데이터)
        //까지 지정해줘야 when으로 넘어가기
        PostDto post1 = new PostDto();
        post1.setTitle("title1");
        post1.setContent("test1");
        post1.setWriter("testw1");

        PostDto post2 = new PostDto();
        post2.setTitle("title2");
        post2.setContent("test2");
        post2.setWriter("testw2");

        List<PostDto> readAllPost = Arrays.asList(post1,post2);
        given(postService.readPostAll()).willReturn(readAllPost);

        //when
        final ResultActions actions = mockMvc.perform(get("/post"))
                .andDo(print());
        //then
        actions.andExpectAll(
                status().isOk(),
                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                jsonPath("$",hasSize(readAllPost.size()))
        );
    }
}