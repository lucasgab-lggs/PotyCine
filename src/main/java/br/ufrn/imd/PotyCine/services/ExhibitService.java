package br.ufrn.imd.PotyCine.services;

import br.ufrn.imd.PotyCine.domain.Event;
import br.ufrn.imd.PotyCine.domain.Exhibit;
import br.ufrn.imd.PotyCine.domain.Movie;
import br.ufrn.imd.PotyCine.domain.Producer;
import br.ufrn.imd.PotyCine.dto.CreateExhibitDto;
import br.ufrn.imd.PotyCine.dto.ProducerDto;
import br.ufrn.imd.PotyCine.repositories.EventRepository;
import br.ufrn.imd.PotyCine.repositories.ExhibitRepository;
import br.ufrn.imd.PotyCine.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExhibitService {
    private final ExhibitRepository exhibitRepository;
    private final MovieRepository movieRepository;
    private final EventRepository eventRepository;
    private final EventService eventService;

    public ExhibitService(ExhibitRepository exhibitRepository, MovieRepository movieRepository, EventRepository eventRepository, EventService eventService) {
        this.exhibitRepository = exhibitRepository;
        this.movieRepository = movieRepository;
        this.eventRepository = eventRepository;
        this.eventService = eventService;
    }

    public Exhibit createExhibit(CreateExhibitDto createExhibitDto){
        Event event = eventRepository.findById(createExhibitDto.eventId())
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        Movie movie = buildMovie(createExhibitDto);

        Exhibit exhibit = new Exhibit();
        exhibit.setMovie(movie);
        exhibit.setEvent(event);
        exhibit.setTime(createExhibitDto.time());

        return exhibitRepository.save(exhibit);
    }

    public List<Exhibit> getExhibitsByEventId(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        return exhibitRepository.findByEvent(event);
    }
    public Exhibit getExhibitById(Long exhibitId) {
        return exhibitRepository.findById(exhibitId)
                .orElseThrow(() -> new RuntimeException("Exibição não encontrada"));
    }
    public void deleteExhibitById(Long exhibitId) {
        if (!exhibitRepository.existsById(exhibitId)) {
            throw new RuntimeException("Exibição não encontrada");
        }
        exhibitRepository.deleteById(exhibitId);
    }
    public Exhibit updateExhibit(Long id, CreateExhibitDto exhibitDto) {
        Exhibit exhibit = getExhibitById(id);

        if (exhibitDto.eventId() != null) {
            Event event = eventService.getEventById(exhibitDto.eventId());
            exhibit.setEvent(event);
        }

        Movie movie = buildMovie(exhibitDto);
        exhibit.setMovie(movie);

        if (exhibitDto.time() != null) exhibit.setTime(exhibitDto.time());

        return exhibitRepository.save(exhibit);
    }
    private Movie buildMovie(CreateExhibitDto dto) {
        Movie movie = new Movie();
        movie.setTitle(dto.movieTitle());
        movie.setDescription(dto.movieDescription());
        movie.setDuration(dto.movieDuration());
        movie.setDirector(dto.movieDirector());
        movie.setScript(dto.movieScript());
        movie = movieRepository.save(movie);
        return movie;
    }

}
