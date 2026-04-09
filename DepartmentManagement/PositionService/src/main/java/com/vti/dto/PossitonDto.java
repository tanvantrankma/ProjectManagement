package com.vti.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PossitonDto {
    private short id;

    private String name;

    public PossitonDto(short id, String name) {
        this.id = id;
        this.name = name;
    }

}

