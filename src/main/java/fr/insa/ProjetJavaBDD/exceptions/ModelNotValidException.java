package fr.insa.ProjetJavaBDD.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ModelNotValidException extends RuntimeException {

    private List<String> messages;

    public ModelNotValidException() {
        this.messages = new ArrayList<>();
    }

}
