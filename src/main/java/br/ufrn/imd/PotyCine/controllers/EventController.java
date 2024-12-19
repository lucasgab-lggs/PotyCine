package br.ufrn.imd.PotyCine.controllers;

import br.ufrn.imd.PotyCine.domain.Event;
import br.ufrn.imd.PotyCine.dto.EventDto;
import br.ufrn.imd.PotyCine.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody EventDto eventDto){
        Event event = eventService.createEvent(eventDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }

    @GetMapping("/until-today")
    public ResponseEntity<List<Event>> getEventsUntilToday() {
        List<Event> events = eventService.getEventsUntilToday();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/producer/{producerId}")
    public ResponseEntity<List<Event>> getEventsByProducer(@PathVariable Long producerId) {
        List<Event> events = eventService.getEventsByProducer(producerId);
        return ResponseEntity.ok(events);
    }
}
