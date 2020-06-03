package com.hollysys.tn.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public final class DateUtil {

	public static final long DAY = 86400000;
	public static final long HOUR = 3600000;
	public static final long MINUTE = 60000;

	private static Calendar calendarGetStartOfDay( Date date ) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime( date );
		calendar.set( Calendar.HOUR_OF_DAY, 0 );
		calendar.set( Calendar.MINUTE, 0 );
		calendar.set( Calendar.SECOND, 0 );
		calendar.set( Calendar.MILLISECOND, 0 );
		return calendar;
	}

	private static Calendar calendarGetStartOfDay( int date ) {
		int y = date / 10000;
		int m = date % 10000 / 100 - 1;
		int d = date % 100;
		Calendar c = Calendar.getInstance();
		c.set( y, m, d, 0, 0, 0 );
		c.set( Calendar.MILLISECOND, 0 );
		return c;
	}
	
	public static Date getStartOfDay( Date date ) {
		return calendarGetStartOfDay( date ).getTime();
	}

	public static Date getStartOfDay( int date ) {
		return calendarGetStartOfDay( date ).getTime();
	}


	public static Date getEndOfDay( Date date ) {
		Calendar c = calendarGetStartOfDay( date );
		c.add( Calendar.HOUR_OF_DAY, 24 );
		return c.getTime();
	}

	public static Date getEndOfDay( int date ) {
		Calendar c = calendarGetStartOfDay( date );
		c.set( Calendar.HOUR_OF_DAY, 24 );
		return c.getTime();
	}


	/**
	 * 使用预设格式格式化日期</br>
	 * 
	 * @param date 日期
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String format( Date date ) {
		return format( date, "yyyy-MM-dd HH:mm:ss" );
	}

	/**
	 * 使用用户格式格式化日期
	 * 
	 * @param date    日期
	 * @param pattern 日期格式
	 * @return
	 */
	public static String format( Date date, String pattern ) {
		return new SimpleDateFormat( pattern ).format( date );
	}

	public static Date formatDate( Date date, String pattern ) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			String s = sdf.format(date);
			return sdf.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String formatUTC( Date date, String pattern ) {
		SimpleDateFormat sdf = new SimpleDateFormat( pattern );
		sdf.setTimeZone( TimeZone.getTimeZone( "GMT" ) );
		return sdf.format( date );
	}

	/**
	 * 在日期上增加数个整月
	 *
	 * @param date 日期
	 * @param n    要增加的月数
	 * @return
	 */
	public static Date addMonth( Date date, int n ) {
		Calendar cal = Calendar.getInstance();
		cal.setTime( date );
		cal.add( Calendar.MONTH, n );
		return cal.getTime();
	}

	public static String getMonth( Date date ) {
		return format( date ).substring( 5, 7 );
	}


	/**
	 * 根据开始日期和结束日期返回时间段内的日期集合
	 *
	 * @param begin
	 * @param end
	 * @return List
	 */
	public static List<Date> getDatesBetweenTwoDate( Date begin, Date end ) {
		begin = getStartOfDay( begin );
		end = getStartOfDay( end );

		List<Date> result = new ArrayList<>();
		while( begin.getTime() <= end.getTime() ) {
			result.add( new Date(begin.getTime()) );
			begin.setTime( begin.getTime() + DAY );
		}
		return result;
	}

	/**
	 * @Method addDay
	 * @Description 查询指定天数后的时间
	 * @Author LuShanyuan
	 * @Date 2019年5月28日 上午9:35:41
	 * @return String
	 */
	public static Date addDay( Date date, int n ) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add( Calendar.DATE, n );
		return c.getTime();
	}

	/**
	 *
	 * @Method addYear
	 * @Description 往后推一年的时间
	 * @Author LuShanyuan
	 * @Date 2019年5月28日 上午9:38:07
	 * @return Date
	 */
	public static Date addYear( Date date, int n ) {
		Calendar c = Calendar.getInstance();
		c.setTime( date );
		c.add( Calendar.YEAR, n );
		return c.getTime();
	}

	public static List<Date> getMonthBetweenTwoDate( Date begin, Date end ) {
		ArrayList<Date> result = new ArrayList<>();

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime( begin );
		min.set( min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1 );

		max.setTime( end );
		max.set( max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2 );

		while( min.before(max) ) {
			result.add( min.getTime() );
			min.add( Calendar.MONTH, 1 );
		}

		return result;
	}

	public static List<Integer> getMonthBetweenTwoDate( int begin, int end ) {
		ArrayList<Integer> result = new ArrayList<>();
		Calendar min = calendarGetStartOfDay(begin);
		min.set( min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1 );

		Calendar max = calendarGetStartOfDay(end);
		max.set( max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2 );

		while( min.before(max) ) {
			result.add( getMonthAsInteger(min.getTime()) );
			min.add( Calendar.MONTH, 1 );
		}

		return result;
	}

	public static Date parseDate( String format, String str ) throws ParseException {
		return new SimpleDateFormat( format ).parse( str );
	}


	public static int getDateAsInteger( Date date ) {
		Calendar c = Calendar.getInstance();
		c.setTime( date );
		int y = c.get( Calendar.YEAR );
		int m = c.get( Calendar.MONTH ) + 1;
		int d = c.get( Calendar.DAY_OF_MONTH );
		return y * 10000 + m * 100 + d;
	}
	
	public static int getMonthAsInteger( Date date ) {
		Calendar c = Calendar.getInstance();
		c.setTime( date );
		int y = c.get( Calendar.YEAR );
		int m = c.get( Calendar.MONTH ) + 1;
		return y * 100 + m;
	}

	public static String getNumOfString(int number){
		String date = String.valueOf(number);
		if(date.length() == 6){
			return date.substring(0,4) + "-" + date.substring(4,6);
		}
		if(date.length() == 8){
			return date.substring(0,4) + "-" + date.substring(4,6) + "-" +date.substring(6,8);
		}
		return date;
	}
	
	public static int getDayCountOfMonth( Date date ) {
		Calendar c = Calendar.getInstance();
		c.setTime( date );
		int y = c.get( Calendar.YEAR );
		int m = c.get( Calendar.MONTH );
		switch( m + 1 ) {
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			case 2:
				if( y % 4 != 0 ) {
					return 28;
				}
				if( y % 100 != 0 ) {
					return 29;
				}
				return (y % 400 == 0) ? 29 : 28;
			default:
				return 31;
		}
	}

	public static Date getStartOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}
	
	public static Date getStartOfMonth(int date) {
		Calendar c = calendarGetStartOfDay( date );
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}
	
	public static Date getEndOfMonth(Date date) {
		date = getStartOfDay( date );
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 0);
		return c.getTime();
	}
	
	public static Date getEndOfMonth(int date) {
		Calendar c = calendarGetStartOfDay( date );
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 0);
		return c.getTime();
	}
	
	public static int getDayOfWeek( Date date ) {
		Calendar c = Calendar.getInstance();
		c.setTime( date );
		return c.get( Calendar.DAY_OF_WEEK );
	}

	public static int getDayOfMonth( Date date ) {
		Calendar c = Calendar.getInstance();
		c.setTime( date );
		return c.get( Calendar.DAY_OF_MONTH );
	}


	public static String mongoDateFormatFromInterval( String interval ) {
		if( interval.equals( "day" ) ) {
			return "%Y%m%d";
		} else if( interval.equals( "week" ) ) {
			return "%YW%V";
		} else if( interval.equals( "month" ) ) {
			return "%Y%m";
		} else if( interval.equals( "year" ) ) {
			return "%Y";
		}
		return "";
	}
	
	public static int getAllWeeks(String year){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			calendar.setTime(sdf.parse(year+"-12-31"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int week = calendar.get(Calendar.WEEK_OF_YEAR);
		if(week != 53){
			week = 52;
		}
		return week;
	}

	public static Date getMonthBegin(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		//设置为1号,当前日期既为本月第一天  
		c.set(Calendar.DAY_OF_MONTH, 1);
		//将小时至0  
		c.set(Calendar.HOUR_OF_DAY, 0);  
		//将分钟至0  
		c.set(Calendar.MINUTE, 0);  
		//将秒至0  
		c.set(Calendar.SECOND,0);  
		//将毫秒至0  
		c.set(Calendar.MILLISECOND, 0);  
		// 获取本月第一天的时间戳  
		return new Date(c.getTimeInMillis());  
	}
	
	public static Date getMonthEnd(Date date) {
		Calendar c = Calendar.getInstance();  
		c.setTime(date);
		
		//设置为当月最后一天
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));  
		//将小时至23
		c.set(Calendar.HOUR_OF_DAY, 23);  
		//将分钟至59
		c.set(Calendar.MINUTE, 59);  
		//将秒至59
		c.set(Calendar.SECOND,59);  
		//将毫秒至999
		c.set(Calendar.MILLISECOND, 999);  
		// 获取本月最后一天的时间戳  
		return new Date(c.getTimeInMillis());
	}
}
