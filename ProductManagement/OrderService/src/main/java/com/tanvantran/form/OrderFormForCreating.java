package com.tanvantran.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderFormForCreating {

    private String orderNumber;
    private Long accountId;
    private String shippingAddressId;
    private short billingAddressId;
    private short orderStatus;

}
