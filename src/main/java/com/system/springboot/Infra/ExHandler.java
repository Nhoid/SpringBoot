package com.system.springboot.Infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice  //DEFINE ESSA CLASSE COMO SENDO A TRATADORA DE ERROS DE TODO O O CODIGO
public class ExHandler {
    @ExceptionHandler(EntityNotFoundException.class)//DEFINE QUAL O TIPO DE ERRO SERÁ TRATADO NESSE METODO ESPECIFICO
    public ResponseEntity<?> Exception404(){

        return ResponseEntity.notFound().build();//RETORNA QUE NÃO FOI ENCONTRADO, NOTFOUND
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)//DEFINE QUAL O TIPO DE ERRO SERÁ TRATADO NESSE METODO ESPECIFICO
    public ResponseEntity<?> Exception400(MethodArgumentNotValidException ex){//RECEBE A PROPRIA EXCEPTION COMO PARAMETRO PARA TRATAR MELHOR
        return ResponseEntity.badRequest().
                body(ex.
                        getFieldErrors().//PEGA DA EXCEPTION TODA A PARTE QUE ESPECIFICA O ERRO
                        stream().
                        map(ErrorsExplain::new).//MAPEIA O ERRO PARA O DTO TRATAR O QUE SERÁ ENVIADO
                        toList());//COLOCA TUDO PARA UMA LISTA
    }
    private record ErrorsExplain(String field, String message){//ESPECIFICA O QUE IRÁ RETORNAR DA EXCEPTION
        public ErrorsExplain(FieldError error){//CONSTROI O DTO COM BASE NA EXCEPTION
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
