package com.bss.mailmon.mapper;

import java.util.Collection;
import java.util.List;

import ma.glasnost.orika.MapperFacade;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class OrikaTransformer<E, DTO> {
    private Class<E> entity;
    private Class<DTO> dto;

    public OrikaTransformer(Class<E> entity, Class<DTO> dto) {
        this.entity = entity;
        this.dto = dto;
    }

    @Autowired
    private MapperFacade mapper;

    public DTO asDTO(E entity) {
        return mapper.map(entity, dto);
    }

    public E asEntity(DTO object) {
        return mapper.map(object, entity);
    }

    public List<E> asListEntity(final Collection<DTO> items) {
        return mapper.mapAsList(items, entity);
    }

    public List<DTO> asListDTO(final Collection<E> items) {
        return mapper.mapAsList(items, dto);
    }
}
