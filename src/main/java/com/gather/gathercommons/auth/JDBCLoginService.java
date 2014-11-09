/*
 * $Id: JDBCLoginService.java 4147 2012-02-01 17:13:24Z kschaefe $
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

import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCLoginService extends LoginService {
    private static final Logger LOG = Logger.getLogger(JDBCLoginService.class);

    /**
     * The connection to the database
     */
    private Connection conn;
    /**
     * If used, defines the JNDI context from which to get a connection to
     * the data base
     */
    private String jndiContext;
    /**
     * When using the DriverManager to connect to the database, this specifies
     * any additional properties to use when connecting.
     */
    private Properties properties;

    /**
     * Create a new JDBCLoginService and initializes it to connect to a
     * database using the given params.
     *
     * @param driver
     * @param url
     */
    public JDBCLoginService(String driver,
                            String url) {
        super(url);

        try {
            Class.forName(driver);
        } catch (Exception e) {
            LOG.warn(e.getMessage());
        }

        this.setUrl(url);
    }


    /**
     * @return the JDBC connection url
     */
    public String getUrl() {
        return getServer();
    }

    /**
     * @param url set the JDBC connection url
     */
    public void setUrl(String url) {
        String old = getUrl();
        setServer(url);
    }

    /**
     * @return JDBC connection properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * @param properties miscellaneous JDBC properties to use when connecting
     *                   to the database via the JDBC driver
     */
    public void setProperties(Properties properties) {
        Properties old = getProperties();
        this.properties = properties;
    }

    public Connection getConnection() {
        return conn;
    }

    public void setConnection(Connection conn) {
        Connection old = getConnection();
        this.conn = conn;
    }

    /**
     * Attempts to get a JDBC Connection from a JNDI javax.sql.DataSource, using
     * that connection for interacting with the database.
     *
     * @throws Exception
     */
    private void connectByJNDI(String userName,
                               char[] password) throws
                                                Exception {
        InitialContext ctx = new InitialContext();
        javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup(jndiContext);
        conn = ds.getConnection(userName,
                                new String(password));
        conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
    }

    /**
     * Attempts to get a JDBC Connection from a DriverManager. If properties
     * is not null, it tries to connect with those properties. If that fails,
     * it then attempts to connect with a user name and password. If that fails,
     * it attempts to connect without any credentials at all.
     * <p/>
     * If, on the other hand, properties is null, it first attempts to connect
     * with a username and password. Failing that, it tries to connect without
     * any credentials at all.
     *
     * @throws Exception
     */
    private void connectByDriverManager(String userName,
                                        char[] password) throws
                                                         Exception {
        if (getProperties() != null) {
            try {
                conn = DriverManager.getConnection(getUrl(),
                                                   getProperties());
                conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            } catch (Exception e) {
                LOG.warn(e.getMessage());
                try {
                    conn = DriverManager.getConnection(getUrl(),
                                                       userName,
                                                       new String(password));
                    conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                } catch (Exception ex) {
                    LOG.warn(e.getMessage());
                    conn = DriverManager.getConnection(getUrl());
                    conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                }
            }
        } else {
            try {
                conn = DriverManager.getConnection(getUrl(),
                                                   userName,
                                                   new String(password));
            } catch (Exception e) {
                LOG.warn(e.getMessage());
                //try to connect without using the userName and password
                conn = DriverManager.getConnection(getUrl());

            }
        }
    }

    /**
     * @param name     user name
     * @param password user password
     * @param server   Must be either a valid JDBC URL for the type of JDBC driver you are using,
     *                 or must be a valid JNDIContext from which to get the database connection
     */
    @Override
    public boolean authenticate(String name,
                                char[] password,
                                String server) throws
                                               Exception {
        //try to form a connection. If it works, conn will not be null
        //if the jndiContext is not null, then try to get the DataSource to use
        //from jndi
        if (jndiContext != null) {
            try {
                connectByJNDI(name,
                              password);
            } catch (Exception e) {
                try {
                    connectByDriverManager(name,
                                           password);
                } catch (Exception ex) {
                    LOG.warn(ex.getMessage());
                    //login failed
                    return false;
                }
            }
        } else {
            try {
                connectByDriverManager(name,
                                       password);
            } catch (Exception ex) {
                LOG.warn(ex.getMessage());
                return false;
            }
        }
        return true;
    }
}
