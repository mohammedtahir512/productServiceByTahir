package com.scaler.productservicebytahir.controllerAdvices;

import com.scaler.productservicebytahir.DTOs.ExceptionDto;
import com.scaler.productservicebytahir.exceptions.CategoryNotFoundException;
import com.scaler.productservicebytahir.exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<ExceptionDto> handleProductNotFoundException() {
      ExceptionDto exceptionDto = new ExceptionDto();
      exceptionDto.setMessage("Something has gone wrong");
      exceptionDto.setDetail("Check the product id. It probably doesn't exist.");
      return ResponseEntity.ok(exceptionDto);
  }
  @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithmeticException(){
      ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Arithmetic Exception occurred");
        exceptionDto.setDetail("Check the arithmetic operation being performed");
        return ResponseEntity.ok(exceptionDto);
  }

  @ExceptionHandler(CategoryNotFoundException.class)
  public ResponseEntity<ExceptionDto> handleCategoryNotFoundException() {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Category not found");
        exceptionDto.setDetail("Check the category id. It probably doesn't exist.");
        return ResponseEntity.ok(exceptionDto);
    }
}
