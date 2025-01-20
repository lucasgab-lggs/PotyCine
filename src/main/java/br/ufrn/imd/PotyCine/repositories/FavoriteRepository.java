package br.ufrn.imd.PotyCine.repositories;

import br.ufrn.imd.PotyCine.domain.Favorite;
import br.ufrn.imd.PotyCine.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavoriteRepository extends CrudRepository<Favorite, Long> {
    List<Favorite> findByUser(User user);
}
