package dev.swapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetResponseDto {
    private Integer diameter;
    private List<String> residents;
}
