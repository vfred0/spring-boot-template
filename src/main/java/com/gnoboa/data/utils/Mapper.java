package com.gnoboa.data.utils;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final ModelMapper mapper;

    public <E, D> D toDto(E entity, Class<D> dtoClass) {
        return mapper.map(entity, dtoClass);
    }

    public <E, D> E toEntity(D dto, Class<E> entityClass) {
        return mapper.map(dto, entityClass);
    }

    public <D, E> List<D> toDtos(List<E> entities, Class<D> dtoClass) {
        return entities.stream()
                .map(entity -> toDto(entity, dtoClass))
                .toList();
    }

    public <D, E> Set<E> toEntities(Set<D> dtos, Class<E> entityClass) {
        return dtos.stream()
                .map(dto -> toEntity(dto, entityClass))
                .collect(Collectors.toSet());
    }
}
