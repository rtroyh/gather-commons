package com.gather.gathercommons.util;

import com.gather.gathercommons.model.DefaultListModel;
import com.gather.gathercommons.model.IListModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 23-05-16
 * Time: 14:52
 */
public class ValidatorTest {
    @DataProvider
    public Object[][] listaMail() {
        return new Object[][]{
                {"rtroy@gather.cl", true},
                {"1", false},
                {"rtroy@.com", false},
                {"pepe@pepe.pepe", true}};
    }

    @DataProvider
    public Object[][] iListModelList() {
        IListModel notEmpty = new DefaultListModel();

        final List<List<Object>> collect = Arrays.stream(new Object[][]{{1, 2, 3}, {4, 5, 6}})
                .map(Arrays::asList)
                .collect(Collectors.toList());
        notEmpty.setRows(collect);

        return new Object[][]{
                {new DefaultListModel(), false},
                {notEmpty, true}};
    }

    @DataProvider
    public Object[][] listas() {
        return new Object[][]{
                {1, false},
                {null, false},
                {new ArrayList<>(), false},
                {new ArrayList<>(Arrays.asList(new Object[]{1, 2, 3, 4})), true}};
    }

    @Test(dataProvider = "listaMail")
    public void testEmailGenerator(Object o1,
                                   Object o2) {
        Assert.assertEquals(Validator.validateMail(o1.toString()),
                            o2);
    }

    @Test(dataProvider = "listas")
    public void testValidateList(Object o1,
                                 Object o2) {
        Assert.assertEquals(Validator.validateList(o1),
                            o2);
    }

    @Test(dataProvider = "iListModelList")
    public void testValidateiListModelList(Object o1,
                                           Object o2) {
        Assert.assertEquals(Validator.isNotEmpty((IListModel) o1),
                            o2);
    }
}
