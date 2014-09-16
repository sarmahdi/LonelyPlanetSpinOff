/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarm.lonelyplanet.exception;

/**
 *
 * @author sarm
 */
public class NodeNotFoundException extends BaseException {
    
      // Class constructors
	/**
	 * Default constructor.  Constructs a new NodeNotFoundException with null
	 * as its error message string.
	 */
	public NodeNotFoundException() {
		// Call base class constructor
		super();
	}

	/**
	 * Constructs the object. Constructs a new NodeNotFoundException with
	 * the specified error message.
	 *
	 * @param message the error message
	 */
	public NodeNotFoundException(String message) {
		// Call base class constructor
		super(message);
	}

	/**
	 * Constructs the object. Constructs a new NodeNotFoundException with
	 * the specified error message and nested exception.
	 *
	 *
	 * @param message the error message
	 * @param cause the nested exception
	 */
	public NodeNotFoundException(String message, Throwable cause) {
		// Call base class constructor
		super(message);
		// Set the cause variable
		this.setCause(cause);
	}

	/**
	 * Constructs the object. Constructs a new NodeNotFoundException with
	 * the specified error message.
	 *
	 * @param cause the nested exception
	 */
	public NodeNotFoundException(Throwable cause) {
		// Call base class constructor
		this.setCause(cause);
	}
    
}
