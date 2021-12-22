package TDD;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * 리뷰를 조회한다.
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AcceptanceTest {
    @LocalServerPort
    private int port;


    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 리뷰_조회_성공() {
        // 준비
        given()
                .accept(MediaType.APPLICATION_JSON_VALUE)

        // 실행
                .when()
                .get("/reviews/1000")

        // 검증
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void 선물하기() {
        given()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .put("/reviews/2")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("TDD training, RestAssured library 좋다.", equalTo(true));
    }

}
