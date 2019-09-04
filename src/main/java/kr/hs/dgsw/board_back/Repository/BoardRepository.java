package kr.hs.dgsw.board_back.Repository;

import kr.hs.dgsw.board_back.Domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByUserId(Long userid);

    // 최신순
    List<Board> findByCategoryIdOrderByCreatedDesc(Long categoryid);
    // 조회순
    List<Board> findByCategoryIdOrderByLookupCountDesc(Long categoryid);
    // 추천순
    List<Board> findByCategoryIdOrderByRecommendCountDesc(Long categoryid);
}
