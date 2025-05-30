package com.productversion.productversionms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggerService.class);

    public void hacerAlgo() {
        logger.info("Ejecutando hacerAlgo()");
        logger.warn("Este es un warning");
        logger.error("Este es un error");
    }
}
