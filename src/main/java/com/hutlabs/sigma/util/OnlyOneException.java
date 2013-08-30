package com.hutlabs.sigma.util;

public class OnlyOneException extends RuntimeException {

	private static final long serialVersionUID = 7845764104257174119L;

	public OnlyOneException() {
		super("There is more than one result in your search!");
	}

}
