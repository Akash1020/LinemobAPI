/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.linemobapi.service;

import java.io.IOException;
import java.net.UnknownHostException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Leandro
 */
@Provider
@PreMatching
public class ContainerRequestFilterImpl implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException, UnknownHostException {
        if (requestContext.getRequest().getMethod().equals("OPTIONS")){
            requestContext.abortWith(Response.status(Response.Status.OK).build());
            return;
        }        
    }

    
}
