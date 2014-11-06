package com.gather.gathercommons;

import com.gather.gathercommons.util.MesesUtil;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created by rodrigotroy on 11/6/14.
 */
public class MesUtilTest {
    private static final Logger LOG = Logger.getLogger(MesUtilTest.class);

    @Test
    public void testMethod() {
        LOG.info("INICIO TEST MESUTIL");

        MesesUtil mesesUtil = new MesesUtil();

        final String[] meses = mesesUtil.getMeses();

        LOG.info(meses);

        for (int i = 0; i < 12; i++) {
            LOG.info(mesesUtil.getNumeroMes(meses[i]));
        }
    }
}
