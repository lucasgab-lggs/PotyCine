package br.ufrn.imd.PotyCine.repositories;

import br.ufrn.imd.PotyCine.domain.Ticket;
import br.ufrn.imd.PotyCine.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
    List<Ticket> findByUser(User user);
}
