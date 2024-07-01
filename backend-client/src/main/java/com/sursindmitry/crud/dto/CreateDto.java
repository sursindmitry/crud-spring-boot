package com.sursindmitry.crud.dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private Set<String> roles;
}