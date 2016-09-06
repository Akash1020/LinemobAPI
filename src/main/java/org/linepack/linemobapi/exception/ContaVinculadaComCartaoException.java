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
public class ContaVinculadaComCartaoException extends RuntimeException {

    public ContaVinculadaComCartaoException() {
    }

    public ContaVinculadaComCartaoException(String message) {
        super(message);
    }

    public ContaVinculadaComCartaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContaVinculadaComCartaoException(Throwable cause) {
        super(cause);
    }

    public ContaVinculadaComCartaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
