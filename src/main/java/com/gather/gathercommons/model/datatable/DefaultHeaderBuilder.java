package com.gather.gathercommons.model.datatable;

import com.gather.gathercommons.util.SecureValue;
import com.gather.gathercommons.util.Validator;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 8/16/18
 * Time: 19:36
 */
public class DefaultHeaderBuilder implements IHeaderBuilder {
    @Override
    public Header createHeader(List<Object> list) {
        Header header = null;
        if (Validator.validateList(list) && list.size() >= 6) {
            header = new Header(SecureValue.cellRowToString(list,
                                                            0),
                                this.resolveDatatype(list.get(1)),
                                Validator.validateNumber(2) ? Integer.valueOf(list.get(2).toString()) : 0,
                                Validator.validateNumber(list.get(3)) && Integer.valueOf(list.get(3).toString()).equals(1),
                                Validator.validateNumber(list.get(4)) && Integer.valueOf(list.get(4).toString()).equals(1),
                                Validator.validateNumber(list.get(5)) ? Double.valueOf(list.get(4).toString()) : 1);
        }

        return header;
    }

    private DataType resolveDatatype(Object rawDataType) {
        if (Validator.validateNumber(rawDataType)) {
            Integer dataTypeNumber = Integer.valueOf(rawDataType.toString());

            for (DataType dataType : DataType.values()) {
                if (dataType.getValue().equals(dataTypeNumber)) {
                    return dataType;
                }
            }
        }

        return DataType.UNKNOWN;
    }
}
