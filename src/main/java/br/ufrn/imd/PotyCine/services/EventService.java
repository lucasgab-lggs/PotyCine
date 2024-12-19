package br.ufrn.imd.PotyCine.services;

import br.ufrn.imd.PotyCine.domain.Event;
import br.ufrn.imd.PotyCine.domain.Producer;
import br.ufrn.imd.PotyCine.dto.EventDto;
import br.ufrn.imd.PotyCine.repositories.EventRepository;
import br.ufrn.imd.PotyCine.repositories.ProducerRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final ProducerRepository producerRepository;

    public EventService(EventRepository eventRepository, ProducerRepository producerRepository) {
        this.eventRepository = eventRepository;
        this.producerRepository = producerRepository;
    }

    public Event createEvent(EventDto eventDto) {
        Producer producer = producerRepository.findById(eventDto.producerId())
                .orElseThrow(() -> new RuntimeException("Produtor não encontrado"));

        Event event = new Event();
        event.setName(eventDto.name());
        event.setDescription(eventDto.description());
        event.setAddress(eventDto.address());
        event.setStartDate(Timestamp.valueOf(eventDto.startDate()));
        event.setEndDate(Timestamp.valueOf(eventDto.endDate()));
        event.setProducer(producer);

        return eventRepository.save(event);
    }

    public List<Event> getEventsUntilToday() {
        LocalDateTime today = LocalDateTime.now();
        return eventRepository.findByEndDateAfter(today);
    }

    public List<Event> getEventsByProducer(Long producerId) {
        Producer producer = producerRepository.findById(producerId)
                .orElseThrow(() -> new RuntimeException("Produtor não encontrado"));
        return eventRepository.findByProducer(producer);
    }

    public Event getEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
    }
}
