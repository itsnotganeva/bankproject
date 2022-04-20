package by.ganevich.mapper.interfaces;

import by.ganevich.dto.CommissionDto;
import by.ganevich.entity.Commission;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommissionMapper {
    CommissionDto toDto(Commission commission);
    Commission toEntity(CommissionDto commissionDto);
    List<Commission> toEntityList(List<CommissionDto> commissionsDto);
    List<CommissionDto> toDtoList(List<Commission> commissions);
}
