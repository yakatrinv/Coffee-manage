package it.academy.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "roleName")
@ToString(of = "roleName")
@Getter
@Setter
public class RoleDto implements Serializable {
    private Integer id;

    private String roleName;
}
