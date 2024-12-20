package br.ufrn.imd.PotyCine.repositories;

import br.ufrn.imd.PotyCine.domain.Event;
import br.ufrn.imd.PotyCine.domain.Exhibit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExhibitRepository extends CrudRepository<Exhibit, Long> {
    List<Exhibit> findByEvent(Event event);

}
