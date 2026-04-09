package com.vti.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccontDto {
    private short id;
    private String email;
    private String username;
    private String fullname;
    private String department;
    private String position;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

}


