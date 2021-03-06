package com.gather.gathercommons.jdbc;

import com.gather.gathercommons.util.Validator;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.Serializable;
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
public class SQLHelper implements Serializable {
    private static final Logger LOG = Logger.getLogger(SQLHelper.class);

    private transient DataSource dataSource;
    private transient Connection connection;

    public SQLHelper(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int createTable(String sentence) throws
                                            SQLException {
        LOG.debug("INICIO CREACION TABLA");

        return this.executeSQL(sentence);
    }

    public int delete(String sentence) throws
                                       SQLException {
        LOG.debug("INICIO DELETE");

        return this.executeSQL(sentence);
    }

    public int update(String sentence) throws
                                       SQLException {
        LOG.debug("INICIO UPDATE");

        return this.executeSQL(sentence);
    }

    public int dropTable(String sentence) throws
                                          SQLException {
        LOG.debug("INICIO DROP TABLE");

        return this.executeSQL(sentence);
    }

    public int insert(String sentence) throws
                                       SQLException {
        LOG.debug("INICIO INSERT");

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

            List<List<Object>> resultset = new ArrayList<>();

            while (haveRS) {
                final ResultSet rs = statement.getResultSet();

                addResultSet(resultset,
                             rs);

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

    private void addResultSet(List<List<Object>> resultset,
                              ResultSet rs) throws
                                            SQLException {
        while (rs.next()) {
            List<Object> row = new ArrayList<>();

            for (int cIndex = 0; cIndex < rs.getMetaData().getColumnCount(); cIndex++) {
                row.add(rs.getObject(cIndex + 1));
            }

            resultset.add(row);
        }
    }

    public String printParameters(Object[] parameters) {
        if (parameters != null && parameters.length > 0) {
            StringBuilder stringBuilder = new StringBuilder();

            for (Object parameter : parameters) {
                if (parameter instanceof String) {
                    stringBuilder.append("'");
                    stringBuilder.append(parameter);
                    stringBuilder.append("'");
                } else {
                    stringBuilder.append(parameter);
                }
                stringBuilder.append(",");
            }

            return stringBuilder.substring(0,
                                           stringBuilder.toString().length() - 1);
        }

        return "";
    }

    public List<List<List<Object>>> call(String sentence,
                                         Object[] parameters,
                                         int[] parametersType,
                                         int[] outputParameter) throws
                                                                SQLException,
                                                                InterruptedException {
        return this.call(sentence,
                         parameters,
                         parametersType,
                         outputParameter,
                         false);
    }

    public List<List<List<Object>>> call(String sentence,
                                         Object[] parameters,
                                         int[] parametersType,
                                         int[] outputParameter,
                                         Boolean waitForOutputParameters) throws
                                                                          SQLException,
                                                                          InterruptedException {
        LOG.debug("INICIO LLAMADO SP: " + sentence + "(" + this.printParameters(parameters) + ")");

        return this.executeCall(sentence,
                                parameters,
                                parametersType,
                                outputParameter,
                                waitForOutputParameters);
    }

    private List<List<List<Object>>> executeCall(String sentence,
                                                 Object[] parameters,
                                                 int[] parametersType,
                                                 int[] inORout,
                                                 Boolean waitForOutputParameters) throws
                                                                                  SQLException,
                                                                                  InterruptedException {
        this.crearConexion();

        CallableStatement statement = null;

        StringBuilder realSentence = buildSPCall(sentence,
                                                 parameters);

        Boolean haveOutputParameters = false;

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
                        if (parameters[x] != null) {
                            statement.setObject(x + 1,
                                                parameters[x].toString().trim(),
                                                parametersType[x]);
                        }
                    }
                }
            }

            Boolean haveRS = statement.execute();

            List<List<List<Object>>> result = new ArrayList<>();

            int updateCount = 0;

            while (haveRS || updateCount != -1) {
                List<List<Object>> resultset = new ArrayList<>();

                if (haveRS) {
                    ResultSet rs = statement.getResultSet();

                    this.addResultSet(resultset,
                                      rs);

                    result.add(resultset);
                } else {
                    updateCount = statement.getUpdateCount();

                    if (updateCount >= 0) {
                        LOG.debug("CONTADOR EN: " + updateCount);
                    } else {
                        LOG.debug("NO HAY MAS REGISTROS.");
                    }
                }

                haveRS = statement.getMoreResults();
            }

            if (haveOutputParameters && waitForOutputParameters) {
                while (!haveRS) {
                    LOG.debug("ESPERANDO AL SP...");
                    Thread.sleep(1000);
                    haveRS = statement.getMoreResults();
                }

                List<List<Object>> resultset = new ArrayList<>();
                List<Object> row = new ArrayList<>();

                for (int x = 0; x < parameters.length; x++) {
                    if (inORout[x] == 0) {
                        LOG.debug("RECUPERANDO PARAMETRO DE SALIDA DESDE LA POSICION: " + x);
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
        int result;

        crearConexion();

        PreparedStatement statement = null;

        try {
            statement = this.connection.prepareStatement(sentence);

            LOG.info(sentence);

            result = statement.executeUpdate();

            LOG.debug("Resultado " + result);
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
            LOG.debug("NUEVA CONEXION");
            this.connection = dataSource.getConnection();
        }
    }

    public void closeConnection() throws
                                  SQLException {
        if (connection != null) {
            LOG.debug("CERRANDO CONEXION");
            connection.close();
        }
    }
}
