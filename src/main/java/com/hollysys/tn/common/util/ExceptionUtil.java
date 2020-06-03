package com.hollysys.tn.common.util;
/*
 * FilePath: null.java
 * Project: hydrogen
 * CreatedAt: 2020/5/27 20:24
 * CreatedBy: l_sy
 * Copyright: (c) 2019 HollySys
 * Task: #1
 * Write a description of the code here.
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {
	private static Log logger = LogFactory.getLog(ExceptionUtil.class);
	public static int ExceptionLogLength = 20000;
	public static String getExceptionStackTrace(Exception e, int length) {
		String trace = null;
		StringWriter sw = new StringWriter(1024);
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        pw.close();
        trace = sw.toString();
        trace = trace.replace("\"", "").replace("'", "").replace("\n", "<br>").replace("\r", "");
		if (trace!=null) {
			if (trace.length()>length) {
				trace = trace.substring(0, length);
			}
		}
		
		return trace;
	}
	
	public static String getExceptionStackTrace2(Exception e, int length) {
		String trace = null;
		StringWriter sw = new StringWriter(1024);
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        pw.close();
        trace = sw.toString();
		if (trace!=null) {
			if (trace.length()>length) {
				trace = trace.substring(0, length);
			}
		}
		//logger.debug(trace);
		return trace;
	}
}
