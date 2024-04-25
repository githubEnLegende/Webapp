package org.oxyl.newro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.oxyl.newro.Display.*;


public class Main {

	private static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		logger.debug("Ceci est un message de debug");
		logger.info("Ceci est un message d'info");
		logger.warn("Ceci est un message de warning");
		logger.error("Ceci est un message d'erreur");
    	feur();

    }
}
