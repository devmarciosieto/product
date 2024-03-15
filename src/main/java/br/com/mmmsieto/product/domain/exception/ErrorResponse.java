package br.com.mmmsieto.product.domain.exception;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private Long timestamp;
    private String message;
    private String path;
    private int status;

}
