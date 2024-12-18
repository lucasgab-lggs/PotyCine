package br.ufrn.imd.PotyCine.repositories;

import br.ufrn.imd.PotyCine.domain.Producer;
import br.ufrn.imd.PotyCine.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface ProducerRepository extends CrudRepository<Producer, Long> {
    boolean existsByUser(User user);
}
