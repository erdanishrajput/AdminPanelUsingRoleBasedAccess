package in.rba.main.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = UserNameAllreadyExistException.class)
	@ResponseStatus
	public @ResponseBody ErrorResponce usernameAllreadyExistException(Exception e) {
		return new ErrorResponce(HttpStatus.CONFLICT.value(), e.getMessage());
		
	}
}