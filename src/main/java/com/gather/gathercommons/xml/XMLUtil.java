package com.gather.gathercommons.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 5/29/17
 * Time: 12:12
 */
public class XMLUtil {
    public static <T> T unmarshall(String xml,
                                   Class aClass) throws
                                                 JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(aClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringBuffer xmlStr = new StringBuffer(xml);

        return (T) unmarshaller.unmarshal(new StreamSource(new StringReader(xmlStr.toString())));
    }

    public static String marshall(Object t,
                                  Class aClass) throws
                                                JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(aClass);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING,
                                   "iso-8859-1");
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                                   true);
        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(t,
                               sw);
        return sw.toString();
    }
}
