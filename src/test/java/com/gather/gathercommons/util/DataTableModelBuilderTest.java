package com.gather.gathercommons.util;

import com.gather.gathercommons.model.DataType;
import com.gather.gathercommons.model.IDataTableModel;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 1/30/18
 * Time: 17:01
 */
public class DataTableModelBuilderTest {
    private static final Logger LOG = Logger.getLogger(DataTableModelBuilderTest.class);

    @Test
    public void testMethod() {
        LOG.info("INICIO TEST");

        final IDataTableModel dataTableModel = new DataTableModelBuilder().createDatatable()
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
        for (List<Object> objects : dataTableModel.getHeaders()) {
            LOG.info(objects);
        }

        for (List<Object> objects : dataTableModel.getRows()) {
            LOG.info(objects);
        }

        Assert.assertTrue(Validator.validateList(dataTableModel.getTitles().get(0)));
        Assert.assertTrue(Validator.validateList(dataTableModel.getHeaders()));
        Assert.assertTrue(Validator.validateList(dataTableModel.getRows()));
    }
}
