package ru.proxima.commons.ajax;

import org.slf4j.Logger;

/**
 * User response produce exception exception
 * @author Шомин Владимир, ЗАО ИВЦ Инсофт
 */
public final class AJAXExecuteException extends Exception {
/**
 * Default constructor, resets exception cause stack
 */
	public AJAXExecuteException() {
		initCause(null);
	}
/**
 * Constructor from string message, resets exception cause stack
 * @param msg Exception message
 */
	public AJAXExecuteException(String msg) {
		super(msg);
		initCause(null);
	}
/**
 * Constructor from throwable cause
 * @param cause Root cause
 */
	public AJAXExecuteException(Throwable cause) {
		super(cause);
	}
/**
 * Constructor from custom message and throwable cause
 * @param msg Custom message
 * @param cause Root cause
 */
	public AJAXExecuteException(String msg, Throwable cause) {
		super(msg, cause);
	}
/**
 * Constructor from throwable cause. Logs cause at debug level and resets stack
 * @param cause Root cause
 * @param logger Logger to log cause at debug level
 */
	public AJAXExecuteException(Throwable cause, Logger logger) {
		logger.debug(cause.getLocalizedMessage(), cause);
		initCause(null);
	}
/**
 * Constructor from custom message and throwable cause. Logs cause at debug
 * level and resets stack
 * @param msg Custom message
 * @param cause Root cause
 * @param logger Logger to log cause at debug level
 */
	public AJAXExecuteException(String msg, Throwable cause, Logger logger) {
		super(msg);
		logger.debug(msg, cause);
		initCause(null);
	}

}
