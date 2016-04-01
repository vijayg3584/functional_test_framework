/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeverything.functestfrmwrk.exception;

/**
 *
 * @author vijay
 */
public class VerifyTestException extends Exception {

    public VerifyTestException(String string) {
        super(string);
    }

    public VerifyTestException(Throwable thrwbl) {
        super(thrwbl);
    }

    public VerifyTestException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
}
