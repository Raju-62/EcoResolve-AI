package com.ecoresolve.exception;
import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.time.LocalDateTime; import java.util.NoSuchElementException;
@RestControllerAdvice public class GlobalExceptionHandler {
 @ExceptionHandler(IllegalArgumentException.class) ResponseEntity<?> bad(IllegalArgumentException e){return body(400,"BAD_REQUEST",e.getMessage());}
 @ExceptionHandler(NoSuchElementException.class) ResponseEntity<?> notFound(NoSuchElementException e){return body(404,"NOT_FOUND",e.getMessage());}
 @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class) ResponseEntity<?> forbidden(Exception e){return body(403,"FORBIDDEN",e.getMessage());}
 @ExceptionHandler(Exception.class) ResponseEntity<?> generic(Exception e){return body(500,"INTERNAL_ERROR",e.getMessage()==null?"Unexpected error":e.getMessage());}
 private ResponseEntity<?> body(int status,String error,String message){return ResponseEntity.status(status).body(java.util.Map.of("timestamp",LocalDateTime.now(),"status",status,"error",error,"message",message));}
}
