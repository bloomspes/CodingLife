package TDD;

import TDD.application.ReviewService;
import TDD.domain.Review;
import TDD.ui.ReviewController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    private MockMvc mockMvc;

    public ReviewControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @MockBean
    private ReviewService reviewService;

    private Long id = 1L;
    private String content = "training";
    private String phone = "010-1111-2222";

    @Test
    void 리뷰는_id가_존재할때_id와_200Status_를_리턴한다() throws Exception {
        // 준비
        given(reviewService.getById()).willReturn(new Review(id, content, phone));

        // 실행
        ResultActions perform = mockMvc.perform(get("/reviews/" + id));

        // 검증
        perform
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(id))
                .andExpect(jsonPath("content").value(content))
                .andExpect(jsonPath("phone").value(phone));
    }
}
