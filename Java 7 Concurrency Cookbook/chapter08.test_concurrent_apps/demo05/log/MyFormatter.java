package demo05.log;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyFormatter extends Formatter {

	/**
	 * 由Logger类调用
	 */
	@Override
	public String format(LogRecord record) {
		StringBuilder sb = new StringBuilder();

		sb.append("[" + record.getLevel() + "] - ");
		sb.append(new Date(record.getMillis()) + " : ");
		sb.append(record.getSourceClassName() + "." + record.getSourceMethodName() + " : ");
		sb.append(record.getMessage() + "\n");
		
		return sb.toString();
	}

}
