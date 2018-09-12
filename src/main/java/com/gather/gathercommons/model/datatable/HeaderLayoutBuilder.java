package com.gather.gathercommons.model.datatable;

import com.gather.gathercommons.util.SecureValue;
import com.gather.gathercommons.util.Validator;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: compass_fecu
 * User: rodrigotroy
 * Date: 9/10/18
 * Time: 18:58
 */
public class HeaderLayoutBuilder implements IHeaderLayoutBuilder {
    @Override
    public HeaderLayout createHeaderLayout(List<Object> list) {
        HeaderLayout header = null;
        if (Validator.validateList(list) && list.size() >= 3) {
            header = new HeaderLayout(Validator.validateNumber(list.get(0)) ? Integer.valueOf(list.get(0).toString()) : 1,
                                      Validator.validateNumber(list.get(1)) ? Integer.valueOf(list.get(1).toString()) : 1,
                                      SecureValue.cellRowToString(list,
                                                                  2));
        }

        return header;
    }
}
