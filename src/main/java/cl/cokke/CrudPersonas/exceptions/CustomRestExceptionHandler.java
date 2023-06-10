package cl.cokke.CrudPersonas.exceptions;

import cl.cokke.CrudPersonas.dto.ApiErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class CustomRestExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ApiErrorDTO> badRequestError(ApiError ex) {

        ApiErrorDTO apiErrorDTO = new ApiErrorDTO(ex.getMessage(), ex.getStatus());
        return new ResponseEntity<ApiErrorDTO>(apiErrorDTO, apiErrorDTO.getStatus());
    }
}
