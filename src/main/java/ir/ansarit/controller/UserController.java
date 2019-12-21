package ir.ansarit.controller;

import ir.ansarit.UserService;
import ir.ansarit.entity.User;
import ir.ansarit.exceptionHandling.JpaProjection;
import ir.ansarit.exceptionHandling.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ComponentScan("ir.ansarit")
public class UserController {

    @Autowired
    private UserService service;


    /*@PostMapping("user")
    public void register(@Valid @RequestBody User user) {
        service.register(user);
    }*/


    @PostMapping("user")
    public ResponseEntity register(@Valid @RequestBody User user) throws Exception {
        return service.register(user);
    }

    @PostMapping("user/update/{id}")
    public void update(@PathVariable("id") long id, @Valid @RequestBody User user) {
        service.update(id, user);
    }

    @GetMapping(value = "user")
    public ResponseEntity read() {
        return new ResponseEntity<>(service.read(), HttpStatus.OK);
    }

    /*@DeleteMapping(value = "user/{id}")
    public void remove(@Valid @PathVariable("id") long id) throws UserNotFoundException {
        service.remove(id);
    }*/


    @PostMapping(value = "user/{id}")
    public void remove(@Valid @PathVariable("id") long id) throws UserNotFoundException {
        service.remove(id);
    }

    @GetMapping("user/{id}")
    public /*List<JpaProjection>*/ User findByUserId(@PathVariable long id) {
        return service.findByUserId(id);
    }


}
