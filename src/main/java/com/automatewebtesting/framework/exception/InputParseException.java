/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.automatewebtesting.framework.exception;

/**
 *
 * @author h.das
 */
public class InputParseException extends Exception {

    public InputParseException(String msg) {
        super(msg);
    }

    public InputParseException(Throwable throwable) {
        super(throwable);
    }

    public InputParseException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
