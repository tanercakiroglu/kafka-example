package model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Customer {

    private Long customerId;
    private String status;
}
