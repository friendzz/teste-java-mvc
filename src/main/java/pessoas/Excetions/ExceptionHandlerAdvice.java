package pessoas.Excetions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(PessoaNaoEncontradaFailedException.class)
    public ResponseEntity handleExceptionPNEF(PessoaNaoEncontradaFailedException e) {
        // log exception
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(NaoDadosFailedException.class)
    public ResponseEntity handleExceptionNDF(NaoDadosFailedException e) {
        // log exception
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(e.getMessage());
    }
}