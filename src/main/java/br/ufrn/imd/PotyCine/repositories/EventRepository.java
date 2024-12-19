package br.ufrn.imd.PotyCine.repositories;

import br.ufrn.imd.PotyCine.domain.Event;
import br.ufrn.imd.PotyCine.domain.Producer;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findByEndDateAfter(LocalDateTime today);

    List<Event> findByProducer(Producer producer);
}
