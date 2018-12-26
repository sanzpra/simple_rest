package es.sanzpra.dev.onlineshipmenttracker.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class BusinessLogicServiceMonitor {
	
	Logger logger = LoggerFactory.getLogger(BusinessLogicServiceMonitor.class);
	
	@AfterReturning(pointcut = "execution(* es.sanzpra.dev.onlineshipmenttracker.service.BusinessLogicService.eventBuilder(..))", returning = "shipmentStatus")
	public void afterAMethod(JoinPoint joinPoint, Object shipmentStatus) {
		if (shipmentStatus!= null) {
			logger.info("Aspect> after business Logic");
			logger.info("----EVENT RECEIVED----"
				+ "\n"+shipmentStatus.toString()					
				+ "\n----------------------");
		}
	 }
	
	@Before("execution(public * es.sanzpra.dev.onlineshipmenttracker.event.ShipmentStatusEventPublisher.printAndPublishEvent(..))")
	public void afterAMethod(JoinPoint joinPoint) {
		logger.info("Aspect> before printAndPublishEvent");
		logger.info(joinPoint.getArgs()[0].toString());
	}

}
