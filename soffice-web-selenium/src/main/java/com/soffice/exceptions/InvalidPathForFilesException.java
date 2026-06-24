package com.soffice.exceptions;

/**
 * InvalidPathForFilesException class with catch the file exception
 * * @author vincent
 */
public class InvalidPathForFilesException extends FrameworkException {

	/**
	 * Throw the invalid path exception when interacting with files
	 * @param message : The detail error message
	 */
	public InvalidPathForFilesException(String message) {
		super(message);
	}
}
