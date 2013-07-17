package demo05.log;

import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLogger {

	private static Handler handler;
	
	public static Logger getLogger(String name) {
		// 取得Logger
		Logger logger = Logger.getLogger(name);
		
		// 设置级别
		logger.setLevel(Level.ALL);
		
		try {
			if (handler == null) {
				handler = new FileHandler("chapter08.test_concurrent_apps/demo05/log/jdklog.log");
				
				// 日志格式
				Formatter format = new MyFormatter();
				handler.setFormatter(format);
			}
			
			if (logger.getHandlers().length == 0) {
				// handler
				logger.addHandler(handler);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return logger;
	}
}

