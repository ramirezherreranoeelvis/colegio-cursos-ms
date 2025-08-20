package com.colegiocursosms.domain.exception;

/**
 * Excepción lanzada cuando se intenta crear una matrícula para un grado y año
 * que ya existen.
 */
public class EnrollmentAlreadyExistsException extends RuntimeException {

      public EnrollmentAlreadyExistsException(String message) {
            super(message);
      }

}