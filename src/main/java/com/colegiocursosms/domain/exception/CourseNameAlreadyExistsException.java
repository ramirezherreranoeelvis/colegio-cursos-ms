package com.colegiocursosms.domain.exception;

/**
 * Excepción lanzada cuando se intenta registrar un curso
 * con un nombre que ya existe en la base de datos.
 */
public class CourseNameAlreadyExistsException extends RuntimeException {

      public CourseNameAlreadyExistsException(String message) {
            super(message);
      }

}
