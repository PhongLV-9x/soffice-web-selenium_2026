package com.soffice.exceptions;

/**
 * Define the not supported exception for the Headless type
 * @author vincent
 */
public class HeadlessNotSupportedException extends IllegalStateException {
    public HeadlessNotSupportedException(String browser) {
        super(String.format("The %s browser does not support headless mode", browser));
    }
}
