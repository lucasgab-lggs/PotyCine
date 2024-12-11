package br.ufrn.imd.PotyCine.repositories;

import br.ufrn.imd.PotyCine.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
