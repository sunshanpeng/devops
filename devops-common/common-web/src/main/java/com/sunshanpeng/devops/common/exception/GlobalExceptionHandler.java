package com.sunshanpeng.devops.common.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sunshanpeng.devops.common.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
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
    BaseResponse<Object> handleControllerException(HttpServletRequest request, Throwable ex) {
        log.error(String.format("request error, uri=[%s], method=[%s], message=[%s]",
                request.getRequestURI(), request.getMethod(), ex.getMessage()), ex);
        return BaseResponse.createFailResult(ex.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    BaseResponse<Object> handleBusinessException(HttpServletRequest request, BusinessException ex) {
        return handleError(request, ex, ex.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    BaseResponse<Object> handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException ex) {
        return handleError(request, ex, ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseBody
    BaseResponse<Object> handleBindException(HttpServletRequest request, Throwable ex) {
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
        return handleError(request, ex, message);
    }
    @ExceptionHandler
    @ResponseBody
    BaseResponse<Object> handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException cex) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!CollectionUtils.isEmpty(cex.getConstraintViolations())) {
            cex.getConstraintViolations().forEach(v -> {
                String invald = v.getInvalidValue() != null ? v.getInvalidValue().toString() : "null";
                stringBuilder.append(v.getPropertyPath()).append(" ").append(v.getMessage()).append(", 当前值: '").append(invald.length() < 50 ? invald : invald.substring(0, 47) + "...").append("'; ");
            });
        }
        String message = stringBuilder.toString();
        return handleError(request, cex, message);
    }

    @ExceptionHandler
    @ResponseBody
    BaseResponse<Object> handleJsonProcessingException(HttpServletRequest request, JsonProcessingException ex) {
        return handleError(request, ex, "请求体的Json格式错误");
    }

    private BaseResponse<Object> handleError(HttpServletRequest request, Throwable ex,
                                                            String message) {
        log.warn(String.format("request error, uri=[%s], method=[%s], message=[%s]",
                request.getRequestURI(), request.getMethod(), message), ex);
        return BaseResponse.createFailResult(message);
    }
}
