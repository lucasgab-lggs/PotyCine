package br.ufrn.imd.PotyCine.dto;

import java.util.Map;

public record ProducerDto(
        Long userId,
        String companyName,
        Map<String, String> portfolio
) {
}
