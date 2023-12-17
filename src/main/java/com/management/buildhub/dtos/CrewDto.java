package com.management.buildhub.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrewDto {
    private Long id;
    private String name;
    private String details;
    private Set<Long> taskIds;
}
