package kr.hs.dgsw.board_back.Controller;

import kr.hs.dgsw.board_back.Domain.Board;
import kr.hs.dgsw.board_back.Domain.Category;
import kr.hs.dgsw.board_back.Domain.User;
import kr.hs.dgsw.board_back.Repository.BoardRepository;
import kr.hs.dgsw.board_back.Repository.CategoryRepository;
import kr.hs.dgsw.board_back.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserRepository userRepository;

    @PostConstruct
    private void init(){
        this.categoryRepository.save(new Category("1학년"));
        this.categoryRepository.save(new Category("2학년"));
        this.categoryRepository.save(new Category("3학년"));

        this.userRepository.save(new User("테스트 유저", "test", "1234", 0L, 10L));

        for(int i=1; i<10; i++){
            this.boardRepository.save(new Board(1L, 1L, "1학년 게시글 "+i, "1학년 내용 "+i, "테스트 유저", 0L, 0L));
        }
        for(int i=1; i<10; i++){
            this.boardRepository.save(new Board(1L, 2L, "2학년 게시글 "+i, "2학년 내용 "+i, "테스트 유저", 0L, 0L));
        }
        for(int i=1; i<10; i++){
            this.boardRepository.save(new Board(1L, 3L, "3학년 게시글 "+i, "3학년 내용 "+i, "테스트 유저", 0L, 0L));
        }
    }

    @GetMapping("/category/getAll")
    public List getAllCategories(){
        return this.categoryRepository.findAll();
    }

    @GetMapping("/category/get/{id}")
    public Category getCategory(@PathVariable Long id){
        return this.categoryRepository.findById(id).orElse(null);
    }

}
