package com.eleks.academy.whoami.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.eleks.academy.whoami.core.exception.GameException;
import com.eleks.academy.whoami.model.response.ApiError;

@RestControllerAdvice
public class GameControllerAdvice {

	@ExceptionHandler(GameException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleGameException(GameException gameException) {
		return gameException::getMessage;
	}

}
