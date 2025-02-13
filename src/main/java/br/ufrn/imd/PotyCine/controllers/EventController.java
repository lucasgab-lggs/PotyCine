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

    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvents(){
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
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

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getEventById(@PathVariable Long eventId) {
        Event event = eventService.getEventById(eventId);
        return ResponseEntity.ok(event);
    }
    @PutMapping("/{eventId}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long eventId, @RequestBody EventDto eventDto) {
        Event event = eventService.updateEvent(eventId, eventDto);
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }
    @DeleteMapping("/{eventId}")
    public ResponseEntity<String> deleteEventById(@PathVariable Long eventId) {
        Event event = eventService.getEventById(eventId);
        eventService.deleteEventById(eventId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Evento removido");
    }

}
