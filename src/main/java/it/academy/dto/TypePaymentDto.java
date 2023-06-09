package it.academy.dto;

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
@ToString(of = "name")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TypePaymentDto implements Serializable {
    private Integer id;

    private String name;

    private Boolean useCreditCard;

    private Boolean usePhoneNumber;
}
