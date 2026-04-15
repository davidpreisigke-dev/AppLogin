package br.applogin.applogin.repository;

import br.applogin.applogin.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository  extends CrudRepository <User, Long> {

    User findById( long id);

    @Query(value = "select * from appLogin.user where email = :email and password = :password", nativeQuery = true)

    public User login (@Param("email") String email, @Param("password") String password);
}
