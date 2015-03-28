package util.logging;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log {
	
	// Log-File
	private Logger logger = Logger.getLogger("(GBSeV) Dienstplan Software");
	
	public Log() {
		
		initiateLogging();
		
	}

	private void initiateLogging() {
		try {
			//SimpleLayout layout = new SimpleLayout();
			HTMLLayout layout = new customHTMLLayout();
			layout.setLocationInfo(true);
			ConsoleAppender consoleAppender = new ConsoleAppender( layout );
			logger.addAppender( consoleAppender );
			FileAppender fileAppender = new FileAppender( layout, "logs" + File.separator + "log-" + DateFormat.getDateInstance().format(new Date(System.currentTimeMillis())).replace(".", "-") + "_" + DateFormat.getTimeInstance().format(new Date(System.currentTimeMillis())).replace(":", "-") + ".html", false );
			logger.addAppender( fileAppender );
			// ALL | DEBUG | INFO | WARN | ERROR | FATAL | OFF:
			logger.setLevel( Level.ALL );
		} catch( Exception ex ) {
			System.out.println( ex );
			LogError("Error registering Logger", ex);
		}
		logger.debug( "Debug-Meldungen: aktiviert" );
		logger.info( "Info-Meldung: aktiviert" );
		logger.warn( "Warn-Meldung: aktiviert" );
		logger.error( "Error-Meldung: aktiviert" );
		logger.fatal( "Fatal-Meldung: aktiviert" );
	}

	public void LogInfo(String message) {
		logger.info(message);
	}

	public void LogDebug(String message) {
		logger.debug(message);
	}

	public void LogWarning(String message) {
		logger.warn(message);
	}

	public void LogError(String message) {
		logger.error(message);
	}

	public void LogError(String message, Throwable t) {
		logger.error(message, t);
	}
	
}