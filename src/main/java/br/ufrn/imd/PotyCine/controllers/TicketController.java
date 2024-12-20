package br.ufrn.imd.PotyCine.controllers;

import br.ufrn.imd.PotyCine.domain.Ticket;
import br.ufrn.imd.PotyCine.dto.TicketDto;
import br.ufrn.imd.PotyCine.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketDto ticketDto) {
        Ticket ticket = ticketService.createTicket(ticketDto);
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }
}
