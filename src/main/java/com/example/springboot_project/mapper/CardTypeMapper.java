package com.example.springboot_project.mapper;

import com.example.springboot_project.dto.CardTypeDTO;
import com.example.springboot_project.entity.CardType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardTypeMapper {

    CardTypeDTO toDTO(CardType cardType);
    CardType dtoToCardType(CardTypeDTO cardTypeDTO);

}
