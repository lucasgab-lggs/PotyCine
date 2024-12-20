package br.ufrn.imd.PotyCine.repositories;

import br.ufrn.imd.PotyCine.domain.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
}
