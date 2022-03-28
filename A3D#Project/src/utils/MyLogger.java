//######################################################################//
//#
//#     Auteur : Théophile Rouchet
//#     Fichier : MyLogger.java
//#     Description : Logger
//#
//######################################################################//
package utils;

import java.util.logging.Logger;

enum Level{
	INFO,
	WARN,
	ERROR,
}

public class MyLogger {

    private static Logger logger = Logger.getGlobal();

	private MyLogger(){};

	public static void loginfo(String message)
	{
		logger.info(message);
	}
	
	public static void logwarning(String message)
	{
		logger.warning(message);
	}

	public static void logsevere(String message)
	{
		logger.severe(message);
	}
}
