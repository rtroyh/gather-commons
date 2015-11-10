package com.gather.gathercommons.jdbc;

import com.gather.gathercommons.util.Validator;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 10-11-15
 * Time: 10:48
 */
public class SQLHelper {
    private static final Logger LOG = Logger.getLogger(SQLHelper.class);

    private DataSource dataSource;
    private Connection connection;

    public SQLHelper(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int createTable(String sentence) throws
                                            SQLException {
        LOG.info("INICIO CREACION TABLA");

        return this.executeSQL(sentence);
    }

    public int delete(String sentence) throws
                                       SQLException {
        LOG.info("INICIO DELETE");

        return this.executeSQL(sentence);
    }

    public int update(String sentence) throws
                                       SQLException {
        LOG.info("INICIO UPDATE");

        return this.executeSQL(sentence);
    }

    public int dropTable(String sentence) throws
                                          SQLException {
        LOG.info("INICIO DROP TABLE");

        return this.executeSQL(sentence);
    }

    public int insert(String sentence) throws
                                       SQLException {
        LOG.info("INICIO INSERT");

        return this.executeSQL(sentence);
    }

    public List<List<Object>> select(String sentence,
                                     Object[] parameters,
                                     int[] parametersType) throws
                                                           SQLException {
        crearConexion();

        PreparedStatement statement = null;

        LOG.info(sentence);

        try {
            statement = connection.prepareStatement(sentence);

            if (parameters != null && parametersType != null) {
                for (int x = 0; x < parameters.length; x++) {
                    LOG.debug("x = " + x);
                    LOG.debug("value = " + parameters[x]);
                    LOG.debug("parametersType = " + parametersType[x]);

                    if (parametersType[x] == Types.DATE) {
                        String date = parameters[x].toString().trim();
                        date = StringUtils.remove(date,
                                                  "'");

                        if (Validator.validateString(date)) {
                            date = StringUtils.remove(date,
                                                      "'");
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(Calendar.YEAR,
                                         Integer.valueOf(date.substring(0,
                                                                        4)));
                            calendar.set(Calendar.MONTH,
                                         Integer.valueOf(date.substring(4,
                                                                        6)) - 1);
                            calendar.set(Calendar.DAY_OF_MONTH,
                                         Integer.valueOf(date.substring(6,
                                                                        8)));

                            LOG.info(DateFormat.getDateTimeInstance().format(calendar.getTime()));

                            statement.setObject(x + 1,
                                                new java.sql.Date(calendar.getTimeInMillis()),
                                                parametersType[x]);
                        } else {
                            statement.setObject(x + 1,
                                                null,
                                                parametersType[x]);
                        }
                    } else {
                        statement.setObject(x + 1,
                                            parameters[x].toString().trim(),
                                            parametersType[x]);
                    }
                }
            }

            boolean haveRS = statement.execute();

            List<List<Object>> resultset = new ArrayList<List<Object>>();

            while (haveRS) {
                final ResultSet rs = statement.getResultSet();

                while (rs.next()) {
                    List<Object> row = new ArrayList<Object>();

                    for (int cIndex = 0; cIndex < rs.getMetaData().getColumnCount(); cIndex++) {
                        row.add(rs.getObject(cIndex + 1));
                    }

                    resultset.add(row);
                }

                haveRS = statement.getMoreResults();
            }

            LOG.info(resultset);

            return resultset;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public List<List<List<Object>>> call(String sentence,
                                         Object[] parameters,
                                         int[] parametersType,
                                         int[] outputParameter) throws
                                                                SQLException {
        LOG.info("INICIO LLAMADO SP: " + sentence);

        return this.executeCall(sentence,
                                parameters,
                                parametersType,
                                outputParameter);
    }

    private List<List<List<Object>>> executeCall(String sentence,
                                                 Object[] parameters,
                                                 int[] parametersType,
                                                 int[] inORout) throws
                                                                SQLException {
        crearConexion();

        CallableStatement statement = null;

        StringBuilder realSentence = buildSPCall(sentence,
                                                 parameters);
        LOG.info(realSentence.toString());

        boolean haveOutputParameters = false;

        try {
            statement = connection.prepareCall(realSentence.toString());

            if (parameters != null && parametersType != null) {
                for (int x = 0; x < parameters.length; x++) {
                    LOG.debug("x = " + x);
                    LOG.debug("value = " + parameters[x]);
                    LOG.debug("parametersType = " + parametersType[x]);

                    if (inORout == null || inORout[x] == 1) {
                        if (parametersType[x] == Types.DATE) {
                            String date = parameters[x].toString().trim();
                            date = StringUtils.remove(date,
                                                      "'");
                            if (Validator.validateString(date)) {
                                date = StringUtils.remove(date,
                                                          "'");
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.YEAR,
                                             Integer.valueOf(date.substring(0,
                                                                            4)));
                                calendar.set(Calendar.MONTH,
                                             Integer.valueOf(date.substring(4,
                                                                            6)) - 1);
                                calendar.set(Calendar.DAY_OF_MONTH,
                                             Integer.valueOf(date.substring(6,
                                                                            8)));

                                statement.setObject(x + 1,
                                                    new java.sql.Date(calendar.getTimeInMillis()),
                                                    parametersType[x]);
                            } else {
                                statement.setObject(x + 1,
                                                    null,
                                                    parametersType[x]);
                            }
                        } else {
                            statement.setObject(x + 1,
                                                parameters[x].toString().trim(),
                                                parametersType[x]);

                        }
                    } else {
                        haveOutputParameters = true;
                        statement.registerOutParameter(x + 1,
                                                       parametersType[x]);
                    }
                }
            }

            boolean haveRS = statement.execute();

            List<List<List<Object>>> result = new ArrayList<List<List<Object>>>();

            while (haveRS) {
                List<List<Object>> resultset = new ArrayList<List<Object>>();

                final ResultSet rs = statement.getResultSet();

                while (rs.next()) {
                    List<Object> row = new ArrayList<Object>();

                    for (int cIndex = 0; cIndex < rs.getMetaData().getColumnCount(); cIndex++) {
                        row.add(rs.getObject(cIndex + 1));
                    }

                    resultset.add(row);
                }

                result.add(resultset);

                haveRS = statement.getMoreResults();
            }

            if (haveOutputParameters) {
                while (!haveRS) {
                    LOG.debug("ESPERANDO AL SP WEON...");
                    haveRS = statement.getMoreResults();
                }
                haveRS = statement.getMoreResults();

                List<List<Object>> resultset = new ArrayList<List<Object>>();
                List<Object> row = new ArrayList<Object>();

                for (int x = 0; x < parameters.length; x++) {
                    if (inORout[x] == 0) {
                        LOG.info("RECUPERANDO PARAMETRO DE SALIDA DESDE LA POSICION: " + x);
                        row.add(statement.getObject(x + 1));
                    }
                }

                resultset.add(row);
                result.add(resultset);
            }

            return result;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    private StringBuilder buildSPCall(String sentence,
                                      Object[] parameters) {
        StringBuilder realSentence = new StringBuilder("{call ");
        realSentence.append(sentence);
        realSentence.append("(");

        if (parameters != null) {
            for (int x = 0; x < parameters.length; x++) {
                if (parameters.length - 1 > x) {
                    realSentence.append("?,");
                } else {
                    realSentence.append("?");
                }
            }
        }
        realSentence.append(")}");

        return realSentence;
    }

    private int executeSQL(String sentence) throws
                                            SQLException {
        int result = 0;

        crearConexion();

        PreparedStatement statement = null;

        try {
            statement = this.connection.prepareStatement(sentence);

            LOG.info(sentence);

            result = statement.executeUpdate();

            LOG.info("Resultado " + result);
        } finally {
            if (statement != null) {
                statement.close();
            }
        }

        return result;
    }

    private void crearConexion() throws
                                 SQLException {
        if (connection == null || connection.isClosed()) {
            LOG.info("NUEVA CONEXION");
            this.connection = dataSource.getConnection();
        }
    }

    public void closeConnection() throws
                                  SQLException {
        if (connection != null) {
            LOG.info("CERRANDO CONEXION");
            connection.close();
        }
    }
}
