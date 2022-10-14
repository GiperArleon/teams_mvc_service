package net.teams.controller;

import lombok.extern.slf4j.Slf4j;
import net.teams.dao.DaoRole;
import net.teams.model.User;
import net.teams.dao.DaoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
public class TeamsController {

    @Autowired
    private DaoUser daoUser;

    @Autowired
    private DaoRole daoRole;

    @GetMapping("/get_users")
    public List<User> getUsers() {
        return daoUser.findAll();
    }

    @GetMapping("/get_telegram_user/{id}")
    public User getUser(@PathVariable("id") String id) {
        return daoUser.FindUserByTelegramId(id);
    }

    @PostMapping("/save_user")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        log.info("user: {}", user);
        if(daoUser.save(user)) {
            return new ResponseEntity<>("User saved", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User save failed", HttpStatus.BAD_REQUEST);
        }
    }
}
