/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarm.lonelyplanet.exception;

/**
 * This is a BaseExceltion which each exception will be derived from
 * It is an example from the online resources.
 * @author sarm
 */


public class BaseException extends Exception {

	/**
	 *  The nested exception
	 */
	private Throwable cause = null;


	// Class constructors
	/**
	 * Default constructor.  Constructs a new BaseException with null
	 * as its error message string.
	 */
	public BaseException() {
		// Call base class constructor
		super();
	}

	/**
	 * Constructs the object. Constructs a new BaseException with
	 * the specified error message.
	 *
	 * @param message the error message
	 */
	public BaseException(String message) {
		// Call base class constructor
		super(message);
	}

	/**
	 * Constructs the object. Constructs a new BaseException with
	 * the specified error message and nested exception.
	 *
	 * @param message the error message
	 * @param cause the nested exception
	 */
	public BaseException(String message, Throwable cause) {
		// Call base class constructor
		super(message);
		// Set the cause variable
		this.setCause(cause);
	}

	/**
	 * Constructs the object. Constructs a new CachingException with
	 * the specified error message.
	 *
	 * @param cause the nested exception
	 */
	public BaseException(Throwable cause) {
		// Call base class constructor
      super(cause.getMessage());
		this.setCause(cause);
	}


	/**
	 * Access method for the cause property.
	 *
	 * @return   the current value of the cause property
	 */
	public Throwable getCause() {
		return cause;
	}

	/**
	 * Sets the value of the cause property.
	 *
	 * @param cause the new value of the cause property
	 */
	public void setCause(Throwable cause) {
		this.cause = cause;
	}


	/**
	 * Prints this exception and its backtrace to the standard error stream.
	 */
	public void printStackTrace() {

		// Print the parent exception
		super.printStackTrace();

		// Check the nested exception
		if (this.getCause() != null) {
			System.err.println("Caused by:");
			this.getCause().printStackTrace();
		}
	}

	/**
	 * Prints this exception and its backtrace to the specified print stream.
	 *
	 * @param printStream PrintStream to use for output
	 */
	public void printStackTrace(java.io.PrintStream printStream) {

		// Print the parent exception
		super.printStackTrace(printStream);

		// Check the nested exception
		if (this.getCause() != null) {
			printStream.println("Caused by:");
			this.getCause().printStackTrace(printStream);
		}
	}

	/**
	 * Prints this exception and its backtrace to the specified print writer
	 *
	 * @param printWriter PrintWriter to use for output
	 */
	public void printStackTrace(java.io.PrintWriter printWriter) {

		// Print the parent exception
		super.printStackTrace(printWriter);

		// Check the nested exception
		if (this.getCause() != null) {
			printWriter.println("Caused by:");
			this.getCause().printStackTrace(printWriter);
		}
	}

	/**
	 * Returns a string representation of this exception object.
	 * It is recommended that all subclasses override
	 * this method. Overrides toString method in class Exception.
	 *
	 * @return a string representation of this object.
	 */
	public String toString() {

		StringBuffer sb = new StringBuffer(this.getClass().getName() + "[super = [");

		sb.append(" cause = ");
		if (this.getCause() != null) {
//			sb.append(this.getCause().toString());
         sb.append(this.getCause().getMessage());
      }
		sb.append(" ");

		sb.append("]");
		return (sb.toString());
	}

}


