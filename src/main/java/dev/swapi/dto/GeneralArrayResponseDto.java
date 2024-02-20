package dev.swapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeneralArrayResponseDto {
    private Integer count;
    private List<Map<String, Object>> results;
}
