package TDD;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final GiftApi giftApi;

    public ReviewService(ReviewRepository reviewRepository, GiftApi giftApi) {
        this.reviewRepository = reviewRepository;
        this.giftApi = giftApi;
    }

    @Transactional(readOnly = true)
    public Review getById(Long id) {
        return reviewRepository
                .findByid(id)
                .orElseThrow(() -> new ReviewNotFoundException("리뷰를 찾을 수 없습니다."));
    }

    @Transactional
    public Review sendGift(Long id) {
        Review review = reviewRepository
                .findByid(id)
                .orElseThrow(() -> new ReviewNotFoundException("리뷰를 찾을 수 없습니다."));

        if (review.getSent()) {
            throw new DuplicateSendGiftException("중복된 상품 리뷰입니다.");
        }

        if (!giftApi.send(review.getPhone())) {
            throw new SendGiftInternalException("internal errors");
        }
    }


}
