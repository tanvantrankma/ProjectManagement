package com.tanvantran.dto;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericResponse<T> {
        private Integer code;
        private HttpStatus status;
        private T data;
        private ErrorResponse error;

}
