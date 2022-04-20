package by.ganevich.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegistrationRequestDto {

    @NotEmpty
    private String login;

    @NotEmpty
    private String name;

    @NotEmpty
    private String type;

    @NotEmpty
    private String password;

}
