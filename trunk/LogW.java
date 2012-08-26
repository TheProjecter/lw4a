/**
 * 
 */
package org.lw4a;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import android.os.Environment;
import android.util.Log;

/**
 * This class wrapps all about logging. It creates a folder called
 * <code>LogP.LOGS_FOLDER_NAME</code> at SD card and inside it, a log file
 * called as log-yyy-MM-dd.txt. One log file for each day and app execution. If
 * the SD card is not mounted LogP cannot generates logs files.
 * 
 * Usage: 
 * <code>
 * 		LogW.info(MyClass.class, "my text to log");
 * 		LogW.error(MyClass.class, "my text to log");
 * 		LogW.warn(MyClass.class, "my text to log");
 * </code>
 * 
 * @author Andres Canavesi - andres.canavesi at gmail.com
 * 
 */
public class LogW {

	public static String TAG = "lw4a";
	public static String LOGS_FOLDER_NAME = "lw4a";
	private static Logger logger = Logger.getLogger(TAG);
	private static boolean configured = false;

	/**
	 * Configures all necessary
	 */
	private static void setUp() {
		try {
			if (!configured) {
				if (0 == Environment.getExternalStorageState().compareTo(Environment.MEDIA_MOUNTED)) {
					Log.i(TAG, "SD is mounted, logs will be stored");
					String appHome = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + LOGS_FOLDER_NAME;
					Log.i(TAG, "Home folder: " + appHome);
					boolean existsAppHome = new File(appHome).exists();

					if (!existsAppHome) {
						Log.i(TAG, "trying to create home folder");
						if (new File(appHome).mkdir()) {
							Log.i(TAG, "home folder created");
						} else {
							Log.w(TAG, "Home folder was not created. Maybe there is not permissions to write on SD card.");
						}
					} else {
						Log.i(TAG, "Home folder already exists, logs will be stored inside it");
					}

					// log's date format
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String today = dateFormat.format(new Date());

					String logName = appHome + "/log-" + today + ".txt";
					Log.i(TAG, "log name: " + logName);
					FileHandler handler = new FileHandler(logName, 1000 * 1000 * 1024, 1, true);
					SimpleFormatter formatterTxt = new SimpleFormatter();
					handler.setFormatter(formatterTxt);

					logger.setLevel(Level.INFO);
					logger.addHandler(handler);

					Log.i(TAG, "LogP confugured");

				} else {
					Log.w(TAG, "SD card is not mounted. Logs will not be stored.");
				}
				configured = true;
			}

		} catch (IOException e) {
			Log.e(TAG, "LogP config error. " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param text
	 */
	private static void info(String text) {
		try {
			setUp();
			Log.i(TAG, text);
			LogRecord record = new LogRecord(Level.INFO, text);
			logger.log(record);
		} catch (Exception e) {

		}

	}

	/**
	 * 
	 * @param clazz
	 * @param text
	 */
	public static void info(Class<?> clazz, String text) {
		info("[" + clazz.getName() + "]" + text);
	}

	/**
	 * 
	 * @param text
	 */
	private static void error(String text) {
		try {
			setUp();
			Log.e(TAG, text);
			LogRecord record = new LogRecord(Level.SEVERE, text);
			logger.log(record);
		} catch (Exception e) {

		}

	}

	/**
	 * 
	 * @param clazz
	 * @param text
	 */
	public static void error(Class<?> clazz, String text) {
		error("[" + clazz.getName() + "]" + text);
	}

	/**
	 * 
	 * @param text
	 * @param thrown
	 */
	private static void error(String text, Throwable thrown) {
		try {
			setUp();
			Log.e(TAG, text);
			logger.log(Level.SEVERE, text, thrown);
		} catch (Exception e) {

		}

	}

	/**
	 * 
	 * @param clazz
	 * @param text
	 * @param thrown
	 */
	public static void error(Class<?> clazz, String text, Throwable thrown) {
		error("[" + clazz.getName() + "]" + text, thrown);
	}

	/**
	 * 
	 * @param text
	 */
	private static void warn(String text) {
		try {
			setUp();
			Log.w(TAG, text);
			LogRecord record = new LogRecord(Level.WARNING, text);
			logger.log(record);
		} catch (Exception e) {

		}

	}

	/**
	 * 
	 * @param clazz
	 * @param text
	 */
	public static void warn(Class<?> clazz, String text) {
		warn("[" + clazz.getName() + "]" + text);
	}

}
