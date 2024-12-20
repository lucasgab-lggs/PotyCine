package br.ufrn.imd.PotyCine.dto;

import java.util.Date;

public record CreateExhibitDto(
        String movieTitle,
        String movieDescription,
        Integer movieDuration,
        String movieDirector,
        String movieScript,
        Long eventId,
        Date time
) {}