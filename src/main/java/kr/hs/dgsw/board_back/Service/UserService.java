package kr.hs.dgsw.board_back.Service;

import kr.hs.dgsw.board_back.Domain.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long userId);
    User findByAccount(String account);
    User findByAccountAndPassword(String account, String password);
    User addUser(User user);
    User editUser(User user);
    boolean deleteById(Long userId);
}
