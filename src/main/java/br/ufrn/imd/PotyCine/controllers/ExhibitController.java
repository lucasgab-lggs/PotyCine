package br.ufrn.imd.PotyCine.controllers;

import br.ufrn.imd.PotyCine.domain.Exhibit;
import br.ufrn.imd.PotyCine.dto.CreateExhibitDto;
import br.ufrn.imd.PotyCine.services.ExhibitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exhibits")
public class ExhibitController {
    private final ExhibitService exhibitService;

    public ExhibitController(ExhibitService exhibitService) {
        this.exhibitService = exhibitService;
    }

    @PostMapping
    public ResponseEntity<Exhibit> createExhibitWithMovie(@RequestBody CreateExhibitDto createExhibitDto) {
        Exhibit exhibit = exhibitService.createExhibit(createExhibitDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(exhibit);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Exhibit>> getExhibitsByEventId(@PathVariable Long eventId) {
        List<Exhibit> exhibits = exhibitService.getExhibitsByEventId(eventId);
        return ResponseEntity.status(HttpStatus.OK).body(exhibits);
    }
}
