package br.ufrn.imd.PotyCine.services;

import br.ufrn.imd.PotyCine.domain.Event;
import br.ufrn.imd.PotyCine.domain.Exhibit;
import br.ufrn.imd.PotyCine.domain.Movie;
import br.ufrn.imd.PotyCine.domain.Producer;
import br.ufrn.imd.PotyCine.dto.CreateExhibitDto;
import br.ufrn.imd.PotyCine.dto.EventDto;
import br.ufrn.imd.PotyCine.dto.ProducerDto;
import br.ufrn.imd.PotyCine.repositories.EventRepository;
import br.ufrn.imd.PotyCine.repositories.ExhibitRepository;
import br.ufrn.imd.PotyCine.repositories.ProducerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final ProducerRepository producerRepository;
    private final ExhibitRepository exhibitRepository;
    private final ProducerService producerService;

    public EventService(EventRepository eventRepository, ProducerRepository producerRepository, ExhibitRepository exhibitRepository, ProducerService producerService) {
        this.eventRepository = eventRepository;
        this.producerRepository = producerRepository;
        this.exhibitRepository = exhibitRepository;
        this.producerService = producerService;
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

    @Transactional
    public void deleteEventById(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));


        exhibitRepository.deleteByEvent(event);
        eventRepository.delete(event);
    }
    public Event updateEvent(Long id, EventDto eventDto) {
        Event event = getEventById(id);

        if (eventDto.name() != null) event.setName(eventDto.name());
        if (eventDto.description() != null) event.setDescription(eventDto.description());
        if (eventDto.address() != null) event.setAddress(eventDto.address());
        if (eventDto.startDate() != null) event.setStartDate(Timestamp.valueOf(eventDto.startDate()));
        if (eventDto.endDate() != null) event.setEndDate(Timestamp.valueOf(eventDto.endDate()));
        if(eventDto.producerId() != null){
            Producer producer = producerService.findProducerById(eventDto.producerId());
            event.setProducer(producer);
        }

        return eventRepository.save(event);
    }
//    String name,
//    String description,
//    String address,
//    LocalDateTime startDate,
//    LocalDateTime endDate,
//    Long producerId
}
