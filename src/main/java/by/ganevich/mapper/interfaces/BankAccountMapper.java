package by.ganevich.mapper.interfaces;

import by.ganevich.dto.BankAccountDto;
import by.ganevich.entity.BankAccount;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ClientMapper.class, BankMapper.class} )
public interface BankAccountMapper {
    BankAccountDto toDto(BankAccount bankAccount);
    BankAccount toEntity(BankAccountDto bankAccountDto);
    List<BankAccount> toEntityList(List<BankAccountDto> bankAccountsDto);
    List<BankAccountDto> toDtoList(List<BankAccount> bankAccounts);
}
