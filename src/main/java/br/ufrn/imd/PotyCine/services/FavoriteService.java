package br.ufrn.imd.PotyCine.services;

import br.ufrn.imd.PotyCine.domain.Event;
import br.ufrn.imd.PotyCine.domain.Favorite;
import br.ufrn.imd.PotyCine.domain.User;
import br.ufrn.imd.PotyCine.dto.FavoriteDto;
import br.ufrn.imd.PotyCine.repositories.EventRepository;
import br.ufrn.imd.PotyCine.repositories.FavoriteRepository;
import br.ufrn.imd.PotyCine.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, UserRepository userRepository, EventRepository eventRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public Favorite createFavorite(FavoriteDto favoriteDto) {
        Event event = eventRepository.findById(favoriteDto.eventId())
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        User user = userRepository.findById(favoriteDto.userId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setEvent(event);

        return favoriteRepository.save(favorite);
    }

    public Favorite getFavoriteById(Long favoriteId) {
        return favoriteRepository.findById(favoriteId)
                .orElseThrow(() -> new RuntimeException("Favorito não encontrado"));
    }

    public void deleteFavoriteById(Long favoriteId) {
        if (!favoriteRepository.existsById(favoriteId)) {
            throw new RuntimeException("Favorito não encontrado");
        }
        favoriteRepository.deleteById(favoriteId);
    }
}
