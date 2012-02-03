package com.gather.gathercommons.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public final class Validator {

    public synchronized static Boolean validateList(Object o) {
        if (o != null) {
            if (o instanceof List) {
                if (!((List<?>) o).isEmpty()) {
                    return true;
                }
            }
        }

        return false;
    }

    public synchronized static Boolean validateList(Object o,
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

    public synchronized static Boolean validateList(String value,
                                                    Map<?, ?> map) {
        if (map != null &&
                value != null) {
            return Validator.validateList(map.get(value));
        }

        return false;
    }

    public synchronized static Boolean validateString(Object o) {
        if (o != null &&
                o instanceof String &&
                ((String) o).length() > 0) {
            return true;
        }

        return false;
    }

    public synchronized static Boolean validateString(Object o,
                                                      Integer size) {
        if (o != null) {
            if (o instanceof String) {
                if (((String) o).length() == size) {
                    return true;
                }
            }
        }

        return false;
    }

    public synchronized static Boolean validateInteger(Object o) {
        if (o != null) {
            if (o instanceof Integer) {
                return true;
            }
        }

        return false;
    }

    public synchronized static Boolean validateNumber(Object o) {
        if (o != null) {
            if (o instanceof Number) {
                return true;
            }
        }

        return false;
    }

    public synchronized static Boolean validateInteger(Object o,
                                                       Integer x) {
        if (o != null) {
            if (o instanceof Integer) {
                if (((Integer) o).equals(x)) {
                    return true;
                }
            }
        }

        return false;
    }

    public synchronized static Boolean validateDouble(Object o) {
        if (o != null) {
            if (o instanceof Double) {
                return true;
            }
        }

        return false;
    }

    public synchronized static Boolean validateBigDecimal(Object o) {
        if (o != null) {
            if (o instanceof BigDecimal) {
                return true;
            }
        }

        return false;
    }

    public synchronized static Boolean validateLong(Object o) {
        if (o != null) {
            if (o instanceof Long) {
                return true;
            }
        }

        return false;
    }

    public synchronized static Boolean validateLong(Object o,
                                                    Integer x) {
        if (o != null) {
            if (o instanceof Long) {
                if (((Long) o).equals(Long.valueOf(x))) {
                    return true;
                }
            }
        }

        return false;
    }
}
