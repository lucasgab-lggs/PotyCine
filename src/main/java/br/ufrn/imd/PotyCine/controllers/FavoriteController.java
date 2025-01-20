package br.ufrn.imd.PotyCine.controllers;

import br.ufrn.imd.PotyCine.domain.Favorite;
import br.ufrn.imd.PotyCine.dto.FavoriteDto;
import br.ufrn.imd.PotyCine.services.FavoriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping
    public ResponseEntity<Favorite> createFavorite(@RequestBody FavoriteDto favoriteDto) {
        Favorite favorite = favoriteService.createFavorite(favoriteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(favorite);
    }

    @DeleteMapping("/{favoriteId}")
    public ResponseEntity<String> deleteFavoriteById(@PathVariable Long favoriteId) {
        favoriteService.deleteFavoriteById(favoriteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Favorito removido");
    }
}
