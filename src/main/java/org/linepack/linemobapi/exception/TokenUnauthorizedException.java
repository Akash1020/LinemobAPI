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
public class TokenUnauthorizedException extends Exception {

    public TokenUnauthorizedException() {
        super("Invalid Token!");
    }

    public TokenUnauthorizedException(String message) {
        super("Invalid Token!");
    }

    public TokenUnauthorizedException(String message, Throwable cause) {
        super("Invalid Token!", cause);
    }

    public TokenUnauthorizedException(Throwable cause) {
        super(cause);
    }

}
