package com.tenco.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenco.blog.handler.exception.DataDeliveryException;

@ControllerAdvice // IoC 대상 (싱글톤 패턴) --> HTML 렌더링 예외에 많이 사용
public class GlobalControllerAdvice {
	@ResponseBody
	@ExceptionHandler(DataDeliveryException.class)
	public String dataDeleveryException(DataDeliveryException e) {
		StringBuffer sb = new StringBuffer();
		sb.append(" <script>");
		sb.append(" alert('" + e.getMessage() + "');");
		sb.append(" window.history.back();");
		sb.append(" </script>");
		return sb.toString();
	}
}
