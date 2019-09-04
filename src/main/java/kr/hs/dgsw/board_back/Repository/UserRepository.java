package kr.hs.dgsw.board_back.Repository;

import kr.hs.dgsw.board_back.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccountAndPassword(String account, String password);
    Optional<User> findByAccount(String account);
}
