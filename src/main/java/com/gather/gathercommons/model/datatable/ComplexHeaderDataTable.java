package com.gather.gathercommons.model.datatable;

import com.gather.gathercommons.model.IComplexHeaderDataTableModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: compass_fecu
 * User: rodrigotroy
 * Date: 9/10/18
 * Time: 18:48
 */
public class ComplexHeaderDataTable<E extends IListHolder> extends DataTable {
    private IComplexHeaderDataTableModel complexHeaderDataTableModel;
    private IHeaderLayoutBuilder headerLayoutBuilder;
    private List<HeaderLayout> headerLayouts;

    public ComplexHeaderDataTable(IComplexHeaderDataTableModel complexHeaderDataTableModel,
                                  IDomainObjectMapper<E> businessObjectMapper) {
        super(complexHeaderDataTableModel,
              businessObjectMapper);

        this.headerLayoutBuilder = new HeaderLayoutBuilder();
        this.complexHeaderDataTableModel = complexHeaderDataTableModel;

        this.build();
    }

    public List<HeaderLayout> getHeaderLayouts() {
        if (headerLayouts == null) {
            headerLayouts = new ArrayList<>();
        }

        return headerLayouts;
    }

    public void setHeaderLayouts(List<HeaderLayout> headerLayouts) {
        this.headerLayouts = headerLayouts;
    }

    private void build() {
        for (List<Object> header : complexHeaderDataTableModel.getHeaderLayout()) {
            this.getHeaderLayouts().add(headerLayoutBuilder.createHeaderLayout(header));
        }
    }
}
