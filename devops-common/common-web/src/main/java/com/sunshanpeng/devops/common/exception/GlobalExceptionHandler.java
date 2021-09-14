package com.sunshanpeng.devops.common.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sunshanpeng.devops.common.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    ResponseEntity<BaseResponse<Void>> handleControllerException(HttpServletRequest request, Throwable ex) {
        log.error(String.format("request error, uri=[%s], method=[%s], message=[%s]",
                request.getRequestURI(), request.getMethod(), ex.getMessage()), ex);
        BaseResponse<Void> failResult = BaseResponse.createFailResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(failResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    ResponseEntity<BaseResponse<Void>> handleExecException(HttpServletRequest request, SystemException ex) {
        BaseResponse<Void> failResult = handleError(request, ex, HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(failResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    ResponseEntity<BaseResponse<Void>> handleParamException(HttpServletRequest request, ParamException ex) {
        BaseResponse<Void> failResult = handleError(request, ex, HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(failResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    ResponseEntity<BaseResponse<Void>> handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException ex) {
        BaseResponse<Void> failResult = handleError(request, ex, HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(failResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseBody
    ResponseEntity<BaseResponse<Void>> handleBindException(HttpServletRequest request, Throwable ex) {
        BindingResult result = null;
        String message = "unknown exception";
        if (ex instanceof MethodArgumentNotValidException) {
            result = ((MethodArgumentNotValidException) ex).getBindingResult();
        } else if (ex instanceof BindException) {
            result = ((BindException) ex).getBindingResult();
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (result != null && !CollectionUtils.isEmpty(result.getFieldErrors())) {
            result.getFieldErrors().forEach(f ->
                    stringBuilder.append(f.getField()).append(" ").append(f.getDefaultMessage())
                            .append(", 当前值: '").append(f.getRejectedValue()).append("'; "));
            message = stringBuilder.toString();
        }
        BaseResponse<Void> failResult = handleError(request, ex, HttpStatus.BAD_REQUEST.value(), message);
        return new ResponseEntity<>(failResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    ResponseEntity<BaseResponse<Void>> handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException cex) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!CollectionUtils.isEmpty(cex.getConstraintViolations())) {
            cex.getConstraintViolations().forEach(v -> {
                String invald = v.getInvalidValue() != null ? v.getInvalidValue().toString() : "null";
                stringBuilder.append(v.getPropertyPath()).append(" ").append(v.getMessage()).append(", 当前值: '").append(invald.length() < 50 ? invald : invald.substring(0, 47) + "...").append("'; ");
            });
        }
        String message = stringBuilder.toString();
        BaseResponse<Void> failResult = handleError(request, cex, HttpStatus.BAD_REQUEST.value(), message);
        return new ResponseEntity<>(failResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    ResponseEntity<BaseResponse<Void>> handleJsonProcessingException(HttpServletRequest request, JsonProcessingException ex) {
        BaseResponse<Void> failResult = handleError(request, ex, HttpStatus.BAD_REQUEST.value(), "请求体的Json格式错误");
        return new ResponseEntity<>(failResult, HttpStatus.BAD_REQUEST);
    }

    private BaseResponse<Void> handleError(HttpServletRequest request, Throwable ex,
                                           Integer code, String message) {
        log.warn(String.format("request error, uri=[%s], method=[%s], message=[%s]",
                request.getRequestURI(), request.getMethod(), message), ex);
        return BaseResponse.createFailResult(code, message);
    }
}
