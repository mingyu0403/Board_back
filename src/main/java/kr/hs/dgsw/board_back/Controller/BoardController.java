package kr.hs.dgsw.board_back.Controller;

import kr.hs.dgsw.board_back.Domain.Board;
import kr.hs.dgsw.board_back.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BoardController {

    @Autowired
    BoardRepository boardRepository;

    @GetMapping("/board/getAll")
    public List getAllBoards(){
        return this.boardRepository.findAll();
    }

    @GetMapping("/board/getSumRecommendCountByUserId/{userid}")
    public Long findAllByUserIdSumRecommendCount(@PathVariable Long userid){
        List<Board> list = this.boardRepository.findByUserId(userid);
        int sum = 0;
        for(int i=0; i<list.size(); i++){
            sum += list.get(i).getRecommendCount();
        }
        return Long.valueOf(sum);
    }

    @GetMapping("/board/getAllByCategoryId/recommend/{categoryid}")
    public List findAllByCategoryIdOrderByRecommendCountDesc(@PathVariable Long categoryid){
        List<Board> list = this.boardRepository.findByCategoryIdOrderByRecommendCountDesc(categoryid);
        return list;
    }
    @GetMapping("/board/getAllByCategoryId/lookup/{categoryid}")
    public List findAllByCategoryIdOrderByLookupCountDesc(@PathVariable Long categoryid){
        List<Board> list = this.boardRepository.findByCategoryIdOrderByLookupCountDesc(categoryid);
        return list;
    }
    @GetMapping("/board/getAllByCategoryId/created/{categoryid}")
    public List findAllByCategoryIdOrderByCreatedDesc(@PathVariable Long categoryid){
        List<Board> list = this.boardRepository.findByCategoryIdOrderByCreatedDesc(categoryid);
        return list;
    }

    @GetMapping("/board/get/{boardid}")
    public Board getBoard(@PathVariable Long boardid){
        return this.boardRepository.findById(boardid).orElse(null);
    }

    @PostMapping("/board/add")
    public Board addBoard(@RequestBody Board board){
        return this.boardRepository.save(board);
    }

    @PutMapping("/board/modify")
    public Board modifyBoard(@RequestBody Board board){
        return this.boardRepository.findById(board.get_id())
                .map(b -> {
                    b.setTitle(Optional.ofNullable(board.getTitle()).orElse(b.getTitle()));
                    b.setContent(Optional.ofNullable(board.getContent()).orElse(b.getContent()));
                    b.setCategoryId(Optional.ofNullable(board.getCategoryId()).orElse(b.getCategoryId()));
                    return this.boardRepository.save(b);
                })
                .orElse(null);
    }

    @PutMapping("/board/addRecommendCount/{boardid}")
    public Board addRecommendCount(@PathVariable Long boardid){
        return this.boardRepository.findById(boardid)
                .map(b -> {
                    b.setRecommendCount(b.getRecommendCount() + 1);
                    return this.boardRepository.save(b);
                })
                .orElse(null);
    }

    @PutMapping("/board/addLookupCount/{boardid}")
    public Board addLookupCount(@PathVariable Long boardid){
        return this.boardRepository.findById(boardid)
                .map(b -> {
                    b.setLookupCount(b.getLookupCount() + 1);
                    return this.boardRepository.save(b);
                })
                .orElse(null);
    }

    @DeleteMapping("board/delete/{id}")
    public boolean deleteBoard(@PathVariable Long id){
        Board found = this.boardRepository.findById(id).orElse(null);
        if(found != null){
            this.boardRepository.deleteById(id);
            return true;
        }
        return false;
    }



}
