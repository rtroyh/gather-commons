package com.gather.gathercommons.util;

import com.gather.gathercommons.model.IDataTableModel;
import com.gather.gathercommons.model.datatable.DataTable;
import com.gather.gathercommons.model.datatable.DataType;
import com.gather.gathercommons.model.datatable.DefaultDomainObjectMapper;
import com.gather.gathercommons.model.datatable.Header;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 8/16/18
 * Time: 15:11
 */
@Test
public class DataTableTest {
    private static final Logger LOG = Logger.getLogger(DataTableTest.class);

    private IDataTableModel dataTableModel;

    @BeforeTest
    private void init() throws
                        Exception {
        this.dataTableModel = DataTableModelBuilder.createDatatable()
                                                   .setTitles(new Object[]{100, "Titulo", "Subtitulo"})
                                                   .addColumn("Oculta",
                                                              DataType.NUMBER,
                                                              2,
                                                              false,
                                                              false,
                                                              0.0)
                                                   .addColumn("Foto",
                                                              DataType.IMAGE,
                                                              0,
                                                              false,
                                                              true,
                                                              0.0)
                                                   .addColumn("UF",
                                                              DataType.NUMBER,
                                                              2,
                                                              false,
                                                              true,
                                                              0.0)
                                                   .addColumn("Nombre",
                                                              DataType.STRING,
                                                              0,
                                                              false,
                                                              true,
                                                              0.0)
                                                   .addColumn("Monto",
                                                              DataType.NUMBER,
                                                              0,
                                                              false,
                                                              true,
                                                              0.0)
                                                   .addColumn("Porcentaje",
                                                              DataType.PERCENTAGE,
                                                              0,
                                                              false,
                                                              true,
                                                              0.0)
                                                   .addRandomRows(10)
                                                   .build();
    }

    @Test
    public void test() {
        LOG.info("INICIO TEST");

        DataTable<List<Object>> dataTable = new DataTable<>(this.dataTableModel,
                                                            new DefaultDomainObjectMapper());

        Assert.assertFalse(dataTable.isEmpty());
        Assert.assertTrue(Validator.validateList(dataTable.getRows()));
        Assert.assertTrue(Validator.validateList(dataTable.getHeaders()));
        Assert.assertTrue(Validator.validateList(dataTable.getTitles()));

        for (Header header : dataTable.getHeaders()) {
            LOG.info(header.getHeaderText() + " | tipo: " + header.getDataType().name());
        }

        for (List<Object> row : dataTable.getRows()) {
            LOG.info(row);
        }
    }
}
