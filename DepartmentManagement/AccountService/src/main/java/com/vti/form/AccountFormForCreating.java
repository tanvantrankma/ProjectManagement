
package com.vti.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccountFormForCreating {
    private String email;
    private String username;
    private String fullname;
    private short departmentId;
    private short positionId;

}


