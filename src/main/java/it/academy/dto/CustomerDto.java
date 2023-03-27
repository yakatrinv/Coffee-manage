package it.academy.dto;

import it.academy.dto.auth.UserAuthDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user"})
@EqualsAndHashCode(of = {"name", "surname", "phone", "email"})
public class CustomerDto implements Serializable {
    private Integer id;

    private String name;

    private String surname;

    private String phone;

    private String email;

    private UserAuthDto user;
}
