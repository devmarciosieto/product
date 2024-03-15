package br.com.mmmsieto.product.app.exceptionhandler;

import br.com.mmmsieto.product.domain.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

    // TODO arrumar para o patrão rfc7807  https://datatracker.ietf.org/doc/html/rfc7807

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleMethodException(
            HttpServletRequest request,
            Exception exception) {

        ErrorResponse error = ErrorResponse
                .builder()
                .timestamp(System.currentTimeMillis())
                .message(exception.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .path(request.getRequestURI())
                .build();

        log.error("[ERROR] payload = {} ", error, exception);

        return ResponseEntity.internalServerError().body(error);
    }

}
