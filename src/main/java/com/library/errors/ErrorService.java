package com.library.errors;

@SuppressWarnings("serial")
public class ErrorService extends Exception {
	
	public ErrorService(String msn) {
		super(msn);
	}
}
