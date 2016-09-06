/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.linemobapi.exception;

/**
 *
 * @author Leandro
 */
public class ContaNotFoundException extends RuntimeException {

    public ContaNotFoundException() {
        super();
    }

    public ContaNotFoundException(String message) {
        super(message);
    }

    public ContaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContaNotFoundException(Throwable cause) {
        super(cause);
    }

    public ContaNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
