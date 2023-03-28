package it.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@Builder
@Getter
@Setter
@ToString(exclude = {"products", "address", "model"})
@NoArgsConstructor
@AllArgsConstructor
public class MachineDto implements Serializable {
    private Integer id;

    private String serialNumber;

    private AddressDto address;

    private ModelDto model;

    private Set<ProductDto> products;
}
