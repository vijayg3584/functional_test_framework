/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vijay.functestfrmwrk.exception;

/**
 *
 * @author vijay
 */
public class PrepareTestException extends Exception {

    public PrepareTestException(String string) {
        super(string);
    }

    public PrepareTestException(Throwable thrwbl) {
        super(thrwbl);
    }

    public PrepareTestException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
}
