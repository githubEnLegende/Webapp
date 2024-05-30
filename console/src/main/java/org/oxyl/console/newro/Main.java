package org.oxyl.console.newro;

import org.oxyl.console.config.SpringCliContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {
        logger.info("Starting Newro...");
        Display quoi = SpringCliContext.getContext().getBean(Display.class);

        quoi.feur();
    }

//	private static void e(){
//		System.out.println("feur");
//	}


}


