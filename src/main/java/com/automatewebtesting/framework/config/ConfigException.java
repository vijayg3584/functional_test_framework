/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.automatewebtesting.framework.config;

/**
 *
 * @author vijay
 */
public class ConfigException extends Exception {

	public ConfigException(String string) {
		super(string);
	}

	public ConfigException(Throwable thrwbl) {
		super(thrwbl);
	}

	public ConfigException(String string, Throwable thrwbl) {
		super(string, thrwbl);
	}
}
