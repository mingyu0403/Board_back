package kr.hs.dgsw.board_back.Service;

import kr.hs.dgsw.board_back.Domain.User;
import kr.hs.dgsw.board_back.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findById(Long userId) {
        return this.userRepository.findById(userId).orElse(null);
    }

    @Override
    public User findByAccount(String account) {
        return this.userRepository.findByAccount(account).orElse(null);
    }

    @Override
    public User findByAccountAndPassword(String account, String password) {
        return this.userRepository.findByAccountAndPassword(account, password).orElse(null);
    }

    @Override
    public User addUser(User user) {
        Optional<User> found = this.userRepository.findByAccount(user.getAccount());
        if(found.isPresent()){
            return null;
        }
        return this.userRepository.save(user);
    }

    @Override
    public User editUser(User user) {
        return this.userRepository.findById(user.get_id())
                .map(u -> {
                    u.setAccount(Optional.ofNullable(user.getAccount()).orElse(u.getAccount()));
                    u.setPassword(Optional.ofNullable(user.getPassword()).orElse(u.getPassword()));
                    u.setName(Optional.ofNullable(user.getName()).orElse(u.getName()));
                    u.setAge(Optional.ofNullable(user.getAge()).orElse(u.getAge()));
                    u.setGender(Optional.ofNullable(user.getGender()).orElse(u.getGender()));
                    u.setAttachmentId(Optional.ofNullable(user.getAttachmentId()).orElse(u.getAttachmentId()));
                    return this.userRepository.save(u);
                })
                .orElse(null);
    }

    @Override
    public boolean deleteById(Long userId) {
        User found = this.findById(userId);
        if(found != null){
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
