
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vijay.functestfrmwrk.exception;

/**
 *
 * @author vijay
 */
public class ExecuteTestException extends Exception {

    public ExecuteTestException(String string) {
        super(string);
    }

    public ExecuteTestException(Throwable thrwbl) {
        super(thrwbl);
    }

    public ExecuteTestException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
}
