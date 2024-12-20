package br.ufrn.imd.PotyCine.services;

import br.ufrn.imd.PotyCine.domain.Event;
import br.ufrn.imd.PotyCine.domain.Ticket;
import br.ufrn.imd.PotyCine.domain.User;
import br.ufrn.imd.PotyCine.dto.TicketDto;
import br.ufrn.imd.PotyCine.repositories.EventRepository;
import br.ufrn.imd.PotyCine.repositories.TicketRepository;
import br.ufrn.imd.PotyCine.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository, EventRepository eventRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public Ticket createTicket(TicketDto ticketDto) {
        User user = userRepository.findById(ticketDto.userId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Event event = eventRepository.findById(ticketDto.eventId())
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setEvent(event);
        ticket.setPrice(ticketDto.price());

        return ticketRepository.save(ticket);
    }

    public List<Ticket> findTicketsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return ticketRepository.findByUser(user);
    }
}
