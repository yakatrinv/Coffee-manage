package it.academy.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static it.academy.utils.Data.ATTR_ADDRESS_ID;
import static it.academy.utils.Data.ATTR_MACHINE_ID;
import static it.academy.utils.Data.ATTR_MODEL_ID;
import static it.academy.utils.Data.ATTR_PRODUCT_ID;
import static it.academy.utils.Data.COL_SERIAL_NUMBER;
import static it.academy.utils.Data.MACHINE_PRODUCT;

@Builder
@Getter
@Setter
@ToString(exclude = {"products", "address", "model"})
@EqualsAndHashCode(of = {"serialNumber"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Machine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = COL_SERIAL_NUMBER)
    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = ATTR_ADDRESS_ID)
    private Address address;

    @ManyToOne
    @JoinColumn(name = ATTR_MODEL_ID)
    private Model model;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = MACHINE_PRODUCT,
            joinColumns = {@JoinColumn(name = ATTR_MACHINE_ID)},
            inverseJoinColumns = {@JoinColumn(name = ATTR_PRODUCT_ID)})
    private Set<Product> products = new HashSet<>();
}
