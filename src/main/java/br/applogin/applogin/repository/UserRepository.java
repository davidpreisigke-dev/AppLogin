package br.applogin.applogin.repository;

import br.applogin.applogin.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository <User, Long> {

    User findById( long id);
}
