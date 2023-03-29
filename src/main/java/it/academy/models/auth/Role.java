package it.academy.models.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import static it.academy.utils.Data.COL_ROLE_NAME;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "roleName")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = COL_ROLE_NAME)
    private String roleName;
}
