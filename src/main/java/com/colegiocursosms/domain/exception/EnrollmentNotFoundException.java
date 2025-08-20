package com.colegiocursosms.domain.exception;

/**
 * Excepción lanzada cuando se intenta actualizar una matrícula que no existe.
 */
public class EnrollmentNotFoundException extends RuntimeException {

      public EnrollmentNotFoundException(String message) {
            super(message);
      }

}