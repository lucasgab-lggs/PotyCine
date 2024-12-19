package br.ufrn.imd.PotyCine.dto;

import java.time.LocalDateTime;

public record EventDto(
        String name,
        String description,
        String address,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Long producerId
) {}