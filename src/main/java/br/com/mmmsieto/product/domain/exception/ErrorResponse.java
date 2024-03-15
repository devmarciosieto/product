package br.com.mmmsieto.product.domain.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ErrorResponse {

    private Long timestamp;
    private String message;
    private String path;
    private int status;

}
