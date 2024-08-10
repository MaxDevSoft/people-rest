package com.maxdevsoft.people.dtos;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountDto {

    @NotBlank
    private String name;

    @NotBlank
    @CPF
    private String cpf;

    @NotNull
    private int age;
    
}
