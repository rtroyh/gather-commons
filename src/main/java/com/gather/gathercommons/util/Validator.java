package com.gather.gathercommons.util;

import com.gather.gathercommons.model.IDataTableModel;
import com.gather.gathercommons.model.IListModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validator {
    public static Boolean validateMail(String mail) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern emailCompiledPattern = Pattern.compile(emailPattern);
        Matcher matcher = emailCompiledPattern.matcher(mail);

        return matcher.matches();
    }

    public static boolean valorEsCero(Object x) {
        boolean valorEsCero = x.equals(0.0) || x.equals(0) || x.equals(0L) || x.equals(0F) || x.equals(new BigDecimal(0));

        if (x instanceof BigDecimal) {
            BigDecimal valor = (BigDecimal) x;
            valorEsCero = valorEsCero || (valor.floatValue() == 0);
        }

        return valorEsCero;
    }

    public static Boolean validateDate(Object o) {
        if (o != null) {
            if (o instanceof java.sql.Date) {
                return true;
            } else if (o instanceof java.util.Date) {
                return true;
            }
        }

        return false;
    }

    public static Boolean validateList(Object o) {
        if (o != null) {
            if (o instanceof List) {
                if (!((List<?>) o).isEmpty()) {
                    return true;
                }
            }
        }

        return false;
    }

    public static Boolean isNotEmpty(IListModel o) {
        if (o != null) {
            return Validator.validateList(o.getRows());
        }

        return false;
    }

    public static Boolean validateList(Object o,
                                       Integer size) {
        if (o != null) {
            if (o instanceof List) {
                if (!((List<?>) o).isEmpty()) {
                    if (((List<?>) o).size() == size) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static Boolean validateList(String value,
                                       Map<?, ?> map) {
        if (map != null && value != null) {
            return Validator.validateList(map.get(value));
        }

        return false;
    }

    public static Boolean validateString(Object o) {
        return o != null && o instanceof String && StringUtils.isNotEmpty((String) o);
    }

    public static Boolean validateString(Object o,
                                         Integer size) {
        return Validator.validateString(o) && ((String) o).length() == size;
    }

    public static Boolean validateString(Object o,
                                         String txt) {
        return Validator.validateString(o) && o.equals(txt);
    }

    public static Boolean validateInteger(Object o) {
        if (o != null) {
            if (o instanceof Integer) {
                return true;
            }
        }

        return false;
    }

    public static Boolean validateInteger(Object o,
                                          Integer x) {
        return Validator.validateInteger(o) && o.equals(x);
    }

    public static Boolean validateNumber(Object o) {
        if (o != null) {
            if (o instanceof Number) {
                return true;
            } else if (o instanceof String) {
                return NumberUtils.isCreatable(o.toString());
            }
        }

        return false;
    }

    public static Boolean validateDouble(Object o) {
        if (o != null) {
            if (o instanceof Double) {
                return true;
            }
        }

        return false;
    }

    public static Boolean validateBigDecimal(Object o) {
        if (o != null) {
            if (o instanceof BigDecimal) {
                return true;
            }
        }

        return false;
    }

    public static Boolean validateLong(Object o) {
        if (o != null) {
            if (o instanceof Long) {
                return true;
            }
        }

        return false;
    }

    public static Boolean validateLong(Object o,
                                       Integer x) {
        if (o != null) {
            if (o instanceof Long) {
                if (o.equals(Long.valueOf(x))) {
                    return true;
                }
            }
        }

        return false;
    }

    public static Boolean validateDataTableModel(IDataTableModel model) {
        if (model != null) {
            if (Validator.validateList(model.getHeaders()) && Validator.validateList(model.getRows())) {
                return true;
            }
        }

        return false;
    }

    public static Boolean validateRUT(String rut) {
        if (Validator.validateString(rut)) {
            return Pattern.matches("^0*(\\d{1,3}(\\.?\\d{3})*)\\-([\\dkK])$",
                                   rut);
        }

        return false;
    }
}
