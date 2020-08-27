package com.nisum.examen.handlers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nisum.examen.dtos.ErrorResponseDTO;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private final HttpHeaders headers;

	public RestResponseEntityExceptionHandler() {
		headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
		final ErrorResponseDTO response = new ErrorResponseDTO();
		response.setMessage("El correo ya registrado.");
		return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();

		List<ErrorResponseDTO> response = fieldErrors.stream().map(fe -> {
			ErrorResponseDTO error = new ErrorResponseDTO();
			error.setMessage(fe.getDefaultMessage());
			return error;
		}).collect(Collectors.toList());

		return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
	}

}
