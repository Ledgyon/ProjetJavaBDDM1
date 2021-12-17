package fr.insa.ProjetJavaBDD.ressouces;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import fr.insa.ProjetJavaBDD.exceptions.FunctionnalProcessException;
import fr.insa.ProjetJavaBDD.exceptions.ModelNotValidException;

public class CommonRessource {
	@ExceptionHandler(ModelNotValidException.class)
    public ResponseEntity<String> handleModelNotValidException(
            ModelNotValidException ex
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessages().toString());
    }

    @ExceptionHandler(FunctionnalProcessException.class)
    public ResponseEntity<String> handleFonctionnalProcessException(
            FunctionnalProcessException ex
    ) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ex.getMessage());
    }
}
