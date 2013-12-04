package com.brakassey.sunproject.exceptions;

public class ResourceException extends Exception {
	private static final long serialVersionUID = 123456789L ;

	public ResourceException(String msg) {
		super("ResourceException: " + msg) ;
	}
}
