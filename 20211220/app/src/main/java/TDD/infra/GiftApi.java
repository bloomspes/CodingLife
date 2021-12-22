package TDD.infra;

import TDD.domain.Review;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GiftApi {

   private final WebClient client;
   private Review r;

   public GiftApi(WebClient.Builder builder) {
      this.client = builder.baseUrl("http://youthcon.seok2.dev/apis/v1/send").build();
   }

   private final Integer AMOUNT = 1000;

   public Boolean send(String phone) {
      return this.client.post()
              .uri("/reviews/" + r.getPhone())
              .body(Mono.just(r), Review.class)
              .retrieve()
              .bodyToMono(Review.class);
   }
}
