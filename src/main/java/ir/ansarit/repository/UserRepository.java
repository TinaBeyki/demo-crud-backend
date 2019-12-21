package ir.ansarit.repository;

import ir.ansarit.entity.User;
import ir.ansarit.entity.UserDTO;
import ir.ansarit.exceptionHandling.JpaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query(value = "insert  into usertbl(id, age , email, name, national_id , phone_number, surname)values(:#{#user.id}, :#{#user.age}, :#{#user.email}, :#{#user.name}, :#{#user.nationalId}, :#{#user.phoneNumber}, :#{#user.surname})", nativeQuery = true)
    void create(@Param("user") User user);

    @Query(value = "select name , surname from usertbl u where u.id ?#{#id}", nativeQuery = true)
    User /*List<JpaProjection> */findByUserId(long id);

    @Override
    Optional<User> findById(Long aLong);

    //error
    @Modifying
    @Query(value = "update usertbl u set u.name =:#{#user.name} , u.surname = #{#user.surname}, u.age = #{#user.age}, u.national_id = #{#user.nationalId}, u.phoneNumber = #{#user.phoneNumber}, u.email = #{#user.email}, u.accounts = #{#user.accounts} where u.id = #{#user.id}", nativeQuery = true)
    void update(@Param("user") User user);


    /*@Modifying
    @Query(value = "delete from usertbl where id = #{#id}", nativeQuery = true)
    void deleteById(@Param("id") long id);*/


}

