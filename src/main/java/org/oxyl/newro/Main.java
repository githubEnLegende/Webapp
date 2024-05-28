package org.oxyl.newro;

import org.oxyl.config.SpringApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {
        logger.info("Starting Newro...");
        Display display = SpringApplicationContext.getContext().getBean(Display.class);

        display.feur();
    }

//	private static void e(){
//		System.out.println("feur");
//	}


}


