package br.ufrn.imd.PotyCine.repositories;

import br.ufrn.imd.PotyCine.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
