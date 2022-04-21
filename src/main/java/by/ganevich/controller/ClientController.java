package by.ganevich.controller;

import by.ganevich.dto.BankAccountDto;
import by.ganevich.dto.ClientDto;
import by.ganevich.dto.RegistrationRequestDto;
import by.ganevich.entity.BankAccount;
import by.ganevich.entity.Client;
import by.ganevich.entity.ClientType;
import by.ganevich.entity.User;
import by.ganevich.mapper.interfaces.BankAccountMapper;
import by.ganevich.mapper.interfaces.ClientMapper;
import by.ganevich.service.BankAccountService;
import by.ganevich.service.ClientService;
import by.ganevich.service.UserService;
import by.ganevich.validator.CustomValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@Slf4j
@Tag(name = "Client controller", description = "To manage clients")
public class ClientController {

    private final ClientService clientService;
    private final CustomValidator<ClientDto> clientValidator;
    private final ClientMapper clientMapper;

    private final BankAccountService bankAccountService;
    private final BankAccountMapper bankAccountMapper;
    private final UserService userService;

    @GetMapping(value = "/clients/{id}")
    public String updateClient(@PathVariable(name = "id") Long id, Model model) {
        Optional<Client> client = clientService.findClientById(id);
        ClientDto clientDto = clientMapper.toDto(client.get());
        model.addAttribute("client", clientDto);
        return "updateClient";
    }

    @GetMapping(value = "/clients")
    public String addClient(Model model) {
        model.addAttribute("client", new RegistrationRequestDto());
        return "createClient";
    }

    @PostMapping(value = "/clients")
    @Operation(
            summary = "Client creation",
            description = "Allows to create a new client"
    )
    public String create(
            @ModelAttribute @Parameter(description = "client to be added to the database")
                    RegistrationRequestDto registrationRequestDto
    ) {
        User u = new User();
        u.setPassword(registrationRequestDto.getPassword());
        u.setLogin(registrationRequestDto.getLogin());
        userService.saveUser(u, "ROLE_CLIENT");

        Client client = new Client();
        client.setName(registrationRequestDto.getName());
        client.setType(ClientType.valueOf(registrationRequestDto.getType()));
        client.setUser(u);
        clientService.save(client);
        return "result";
    }

    @GetMapping(value = "/clients/get")
    @Operation(
            summary = "Reading clients",
            description = "Allows to read all clients"
    )
    public ResponseEntity<List<ClientDto>> read() {
        log.info("REST: Read clients is called");
        final List<Client> clients = clientService.readAll();
        List<ClientDto> clientsDto = clientMapper.toDtoList(clients);
        log.info("REST: Reading of clients was successful");
        return new ResponseEntity<>(clientsDto, HttpStatus.OK);
    }

    @GetMapping(value = "/clients/get/{id}")
    @Operation(
            summary = "Reading client",
            description = "Allows to read specific client by id"
    )
    public String read(@PathVariable(name = "id") @Parameter(description = "id of client") Long id, Model model) {
        log.info("REST: Read client with id" + id + " is called");
        final Optional<Client> client = clientService.findClientById(id);
        ClientDto clientDto = clientMapper.toDto(client.get());
        model.addAttribute("client", clientDto);

        List<BankAccount> bankAccounts = bankAccountService.findBankAccountByClientId(id);
        List<BankAccountDto> bankAccountsDto = bankAccountMapper.toDtoList(bankAccounts);

        model.addAttribute("accounts", bankAccountsDto);

        log.info("REST: Reading of client with id" + id + " was successful");
        return "client";
    }

    @PostMapping(value = "/clients/{id}")
    @Operation(
            summary = "Client update",
            description = "Allows to update specific client by id"
    )
    public String update(@ModelAttribute ClientDto clientDto, @PathVariable(name = "id") Long id) {
        Optional<Client> findClient = clientService.findClientById(id);
        ClientDto findClientDto = clientMapper.toDto(findClient.get());
        findClientDto.setName(clientDto.getName());
        findClientDto.setType(clientDto.getType());
        Client client = clientMapper.toEntity(findClientDto);
        clientService.save(client);
        return "result";
    }

    @PostMapping(value = "/clients/delete/{id}")
    @Operation(
            summary = "Client deletion",
            description = "Allows to delete specific client by id"
    )
    public String delete(
            @PathVariable(name = "id") @Parameter(description = "id of client") Long id
    ) {
        log.info("REST: Delete client with id" + id + " is called");
        clientService.deleteClientById(id);

        log.info("REST: Client with id" + id + " was removed successful");
        return "result";
    }

}
