package com.colegiocursosms.domain.exception;

/**
 * Excepción lanzada cuando se intenta registrar un aula
 * con un número que ya existe en la base de datos.
 */
public class ClassroomNumberAlreadyExistsException extends RuntimeException {

  public ClassroomNumberAlreadyExistsException(String message) {
    super(message);
  }

}