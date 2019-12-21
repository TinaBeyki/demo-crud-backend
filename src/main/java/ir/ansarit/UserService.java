package ir.ansarit;

import ir.ansarit.entity.User;
import ir.ansarit.entity.UserDTO;
import ir.ansarit.exceptionHandling.JpaProjection;
import ir.ansarit.exceptionHandling.UserNotFoundException;
import ir.ansarit.exceptionHandling.WrongEmailFormatException;
import ir.ansarit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@EnableJpaRepositories
@ComponentScan("ir.ansarit")
public class UserService {

    @Autowired
    private UserRepository repository;

    /*public void register(User user){
        repository.create(user);}*/


    public ResponseEntity register(User user) throws Exception {
        if(/*user.getEmail().matches("@gmail.com")*/ user.getEmail().contains("@gmail.com")) {
            return new ResponseEntity(repository.save(user), HttpStatus.OK);
        }else{
                throw new WrongEmailFormatException();
        }
    }

    public void update(long id, User user) {
        Optional<User> users = repository.findById(id);
        User userEntity =  users.get();
        userEntity.setName(user.getName());
        userEntity.setSurname(user.getSurname());
        userEntity.setEmail(user.getEmail());
        userEntity.setNationalId(user.getNationalId());
        userEntity.setAge(user.getAge());
        repository.save(userEntity);
        /*repository.update(user);*/
    }



    public void remove(long id) throws UserNotFoundException {
        /*if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new UserNotFoundException();
        }*/
        repository.deleteById(id);
    }

    public Iterable<User> read(){
        return repository.findAll();
    }

    //instead of returning an interface -> return a DTO class
    /*public List<UserDTO> read() {

        List<UserDTO> userDTOList = new ArrayList<>();
        UserDTO  userDTO=new UserDTO();

        List<User> all = repository.findAll();
        for (User user : all) {
             userDTO.setEmail(user.getEmail());
             userDTOList.add(userDTO);
             return userDTOList;
        }
        return null;*/

    public User /*List<JpaProjection> */findByUserId(long id){
        return repository.findByUserId(id);
    }

}
