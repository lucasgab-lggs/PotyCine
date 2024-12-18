package br.ufrn.imd.PotyCine.controllers;

import br.ufrn.imd.PotyCine.domain.Producer;
import br.ufrn.imd.PotyCine.dto.ProducerDto;
import br.ufrn.imd.PotyCine.services.ProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producers")
public class ProducerController {

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping
    public ResponseEntity<Producer> createProducer(@RequestBody ProducerDto createProducerDto) {
        Producer producer = producerService.createProducer(createProducerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(producer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producer> getProducerById(@PathVariable Long id){
        Producer producer = producerService.findProducerById(id);
        return ResponseEntity.status(HttpStatus.OK).body(producer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producer> updateProducer(@PathVariable Long id, @RequestBody ProducerDto producerDto) {
        Producer producer = producerService.updateProducer(id, producerDto);
        return ResponseEntity.status(HttpStatus.OK).body(producer);
    }
}
