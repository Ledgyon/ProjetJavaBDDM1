package fr.insa.ProjetJavaBDD.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class FunctionnalProcessException extends Exception {
	public FunctionnalProcessException(String message) {
        super(message);
    }
}
