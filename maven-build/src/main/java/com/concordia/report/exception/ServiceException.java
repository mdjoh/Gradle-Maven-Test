package com.concordia.report.exception;

public class ServiceException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6852768609975772363L;

	public ServiceException(String errorMessage) {
		super(errorMessage);
	}
}