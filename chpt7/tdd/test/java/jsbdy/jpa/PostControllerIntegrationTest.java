package jsbdy.jpa;

import jsbdy.jpa.entity.PostEntity;
import jsbdy.jpa.repository.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)//얘는 모든 빈을 테스트하기 위한 어노테이션

@AutoConfigureMockMvc
//웹 애플리케이션에서 컨트롤러를 테스트할 때, 서블릿 컨테이너를 모킹하기 위해서
// @WebMvcTest를 사용하거나 @AutoConfigureMockMvc를 사용
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@AutoConfigureTestDatabase
public class PostControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @Before
    //테스트 진행하면서 임의의 엔티티들 많이 만들어줘야 해서
    //아예 함수로 빼준 상황
    public void setEntities(){
        createTestPost("first post", "first post content", "first writer");
        createTestPost("second post", "second post content", "second writer");
    }


    //read 테스트를 할 때 아이디로 읽어오기 위해서
    @Test
    public void givenVadlidId_whenReadPost_then200() throws Exception{
        //given
        Long id = createTestPost("Read Post",  "Created on readPost()", "readPost" );

        //when
        final ResultActions actions = mockMvc.perform(get("/post/{id}",id))
                .andDo(print());

        //then
        actions
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.title", is("Read Post")),
                        jsonPath("$.content", is("Created on readPost()")),
                        jsonPath("$.writer", is("readPost"))
                );
    }

    //테스트용 엔티티 만들어주는 함수

    private Long createTestPost(String title , String content, String writer){

        PostEntity postEntity = new PostEntity();

        postEntity.setTitle(title);
        postEntity.setContent(content);
        postEntity.setWriter(writer);
        return postRepository.save(postEntity).getId();

    }
}