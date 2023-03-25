package it.academy.models.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_LOGIN;
import static it.academy.utils.Data.ATTR_PASSWORD;
import static it.academy.utils.Data.ATTR_ROLE_ID;
import static it.academy.utils.Data.ATTR_USER_ID;
import static it.academy.utils.Data.USER_ROLE;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString(exclude = "roles")
@EqualsAndHashCode(exclude = "roles")//(of = {ATTR_ID, ATTR_LOGIN, ATTR_PASSWORD})
@Table
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String salt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = USER_ROLE,
            joinColumns = {@JoinColumn(name = ATTR_USER_ID)},
            inverseJoinColumns = {@JoinColumn(name = ATTR_ROLE_ID)})
    private Set<Role> roles = new HashSet<>();
}
