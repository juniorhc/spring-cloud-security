package com.in28minutes.microservices.netflixzuulapigatewayserver;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ZuulLoggingFilter extends ZuulFilter{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean shouldFilter() {
		return true;
	} //should be executed or not

	@Override
	public Object run() {
		HttpServletRequest request =
				RequestContext.getCurrentContext().getRequest();
		logger.info("request -> {} request uri -> {}", 
				request, request.getRequestURI());
		return null;
	}
	//result of that is
	//URL: http://localhost:8765/currency-exchange-service/currency-exchange/from/USD/to/INR
	//request -> org.springframework.cloud.netflix.zuul.filters.pre.Servlet30RequestWrapper@4cccc82 request uri -> /currency-exchange-service/currency-exchange/from/USD/to/INR

	@Override
	public String filterType() {
		return "pre";
	} //filter should be executed before or after the request come. pre; post; error

	@Override
	public int filterOrder() {
		return 1;
	} //if we have many filter, this is the order priority
}
