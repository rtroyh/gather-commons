/*
 * $Id: LoginService.java 3661 2010-04-13 13:19:47Z kleopatra $
 *
 * Copyright 2004 Sun Microsystems, Inc., 4150 Network Circle,
 * Santa Clara, California 95054, U.S.A. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package com.gather.gathercommons.auth;

import javax.swing.*;
import java.awt.*;

public abstract class LoginService {
    private SwingWorker<Boolean, Void> loginWorker;

    /*
     * Controls the authentication behaviour to be either synchronous or
     * asynchronous
     */
    private boolean synchronous;

    private String server;

    public LoginService() {
    }

    public LoginService(String server) {
        setServer(server);
    }

    /**
     * This method is intended to be implemented by clients wishing to
     * authenticate a user with a given password. Clients should implement the
     * authentication in a manner that the authentication can be cancelled at
     * any time.
     *
     * @param name     username
     * @param password password
     * @param server   server (optional)
     * @return <code>true</code> on authentication success
     * @throws Exception
     */
    public abstract boolean authenticate(String name,
                                         char[] password,
                                         String server) throws
                                                        Exception;

    /**
     * Called immediately after a successful authentication. This method should
     * return an array of user roles or null if role based permissions are not
     * used.
     *
     * @return per default <code>null</code>
     */
    public String[] getUserRoles() {
        return null;
    }

    /**
     * Notifies the CorredorLoginService that an already running authentication request
     * should be cancelled. This method is intended to be used by clients who
     * want to provide user with control over cancelling a long running
     * authentication request.
     */
    public void cancelAuthentication() {
        if (loginWorker != null) {
            loginWorker.cancel(true);
        }
    }

    /**
     * This method starts the authentication process and is either synchronous
     * or asynchronous based on the synchronous property
     *
     * @param user     user
     * @param password password
     * @param server   server
     * @throws Exception
     */
    public void startAuthentication(final String user,
                                    final char[] password,
                                    final String server) throws
                                                         Exception {
        if (getSynchronous()) {
            try {
                if (authenticate(user,
                                 password,
                                 server)) {
                } else {
                }
            } catch (Throwable e) {

            }
        } else {
            loginWorker = new SwingWorker<Boolean, Void>() {
                @Override
                protected Boolean doInBackground() throws
                                                   Exception {
                    try {
                        final boolean result = authenticate(user,
                                                            password,
                                                            server);
                        if (isCancelled()) {
                            EventQueue.invokeLater(new Runnable() {
                                public void run() {
                                }
                            });
                            return false;
                        }
                        EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                if (result) {

                                } else {

                                }
                            }
                        });
                        return result;
                    } catch (final Throwable failed) {
                        return false;
                    }
                }
            };

            loginWorker.execute();
        }
    }

    /**
     * Get the synchronous property
     *
     * @return the synchronous property
     */
    public boolean getSynchronous() {
        return synchronous;
    }


    /**
     * @return Returns the server.
     */
    public String getServer() {
        return server;
    }

    /**
     * @param server The server to set.
     */
    public void setServer(String server) {
        String old = getServer();
        this.server = server;
    }
}
