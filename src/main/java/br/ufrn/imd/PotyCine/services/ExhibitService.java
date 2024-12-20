package br.ufrn.imd.PotyCine.services;

import br.ufrn.imd.PotyCine.domain.Event;
import br.ufrn.imd.PotyCine.domain.Exhibit;
import br.ufrn.imd.PotyCine.domain.Movie;
import br.ufrn.imd.PotyCine.dto.CreateExhibitDto;
import br.ufrn.imd.PotyCine.repositories.EventRepository;
import br.ufrn.imd.PotyCine.repositories.ExhibitRepository;
import br.ufrn.imd.PotyCine.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExhibitService {
    private final ExhibitRepository exhibitRepository;
    private final MovieRepository movieRepository;
    private final EventRepository eventRepository;

    public ExhibitService(ExhibitRepository exhibitRepository, MovieRepository movieRepository, EventRepository eventRepository) {
        this.exhibitRepository = exhibitRepository;
        this.movieRepository = movieRepository;
        this.eventRepository = eventRepository;
    }

    public Exhibit createExhibit(CreateExhibitDto createExhibitDto){
        Event event = eventRepository.findById(createExhibitDto.eventId())
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        Movie movie = new Movie();
        movie.setTitle(createExhibitDto.movieTitle());
        movie.setDescription(createExhibitDto.movieDescription());
        movie.setDuration(createExhibitDto.movieDuration());
        movie.setDirector(createExhibitDto.movieDirector());
        movie.setScript(createExhibitDto.movieScript());
        movie = movieRepository.save(movie);

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
}
