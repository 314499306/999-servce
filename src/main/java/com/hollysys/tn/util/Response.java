package com.hollysys.tn.util;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 响应对象
 *
 * @author 
 * @since 1.0.0
 */
public class Response {

    private static final String OK = "操作成功!";
    private static final String ERROR = "操作失败!";

    private boolean success;
    private Meta meta;
    private Object data;

    public Response success() {
        this.meta = new Meta(true, OK);
        this.success=true;
        return this;
    }

    public Response success(Object data) {
        this.meta = new Meta(true, OK);
        this.success=true;
        this.data = data;
        return this;
    }
    
    public Response successItem(Object items) {
    	this.meta = new Meta(true, OK);
        this.success=true;
        return this;
    }

    public Response failure() {
        this.meta = new Meta(false, ERROR);
        this.success=false;
        return this;
    }

    public Response failure(String message) {
        this.meta = new Meta(false, message);
        this.success=false;
        return this;
    }

    public Response failure(String message, Exception e) {
        this.meta = new Meta(false, message);
        StringWriter strWriter=new StringWriter();
		PrintWriter writer=new PrintWriter(new BufferedWriter(strWriter));
		e.printStackTrace(writer);
		writer.flush();
		strWriter.flush();
		System.err.println(strWriter.getBuffer().toString());
        this.meta.setStackTraceMessage(strWriter.getBuffer().toString());
        this.success=false;
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public Object getData() {
        return data;
    }


	public class Meta {

        private boolean success;
        private String message;
        private String stackTraceMessage;

        public Meta(boolean success) {
            this.success = success;
        }

        public Meta(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

		public String getStackTraceMessage() {
			return stackTraceMessage;
		}

		public void setStackTraceMessage(String stackTraceMessage) {
			this.stackTraceMessage = stackTraceMessage;
		}
        
        
    }

	public boolean isSuccess() {
		return success;
	}	
	
}
