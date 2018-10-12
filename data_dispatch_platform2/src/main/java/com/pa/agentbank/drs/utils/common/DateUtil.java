package com.pa.agentbank.drs.utils.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pa.agentbank.drs.job.FileOperationJob;

public class DateUtil {
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
	
	private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	private static ThreadLocal<SimpleDateFormat> ThreadDateTime = new ThreadLocal<SimpleDateFormat>();
	
	
	public static SimpleDateFormat DateTimeInstance() {
		SimpleDateFormat df = ThreadDateTime.get();
		if (df == null) {
			df = new SimpleDateFormat(DATETIME_FORMAT);
			ThreadDateTime.set(df);
		}
		
		return df;
	}
	
	public static String currentDateTime() {
		return DateTimeInstance().format(new Date());
	}

	public static String fomatByType(String formatType, Date date) {
		// TODO Auto-generated method stub
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatType);
		return simpleDateFormat.format(date);
	}

	public static Date parseByType(String formatType, String date) {
		// TODO Auto-generated method stub
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatType);
		try {
			return simpleDateFormat.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date day(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, day);
		return calendar.getTime();
	}

	public static Date getDateWithHMS(String hms) {
		// TODO Auto-generated method stub
		
		try {
			String[] split = hms.split(":");
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.set(Calendar.HOUR, Integer.valueOf(split[0]));
			c.set(Calendar.MINUTE, Integer.valueOf(split[1]));
			c.set(Calendar.SECOND, Integer.valueOf(split[2]));
			return c.getTime();
		} catch (Exception e) {
			logger.error("Hour second conversion to date format error ",e);
		}
		return null;
	}

}
