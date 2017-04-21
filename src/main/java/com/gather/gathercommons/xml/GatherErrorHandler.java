package com.gather.gathercommons.xml;

import com.sun.org.apache.xml.internal.utils.DefaultErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 4/21/17
 * Time: 15:24
 */
public class GatherErrorHandler extends DefaultErrorHandler {

    /**
     * Receive notification of a recoverable error.
     * <p>
     * <p>This corresponds to the definition of "error" in section 1.2
     * of the W3C XML 1.0 Recommendation.  For example, a validating
     * parser would use this callback to report the violation of a
     * validity constraint.  The default behaviour is to take no
     * action.</p>
     * <p>
     * <p>The SAX parser must continue to provide normal parsing events
     * after invoking this method: it should still be possible for the
     * application to process the document through to the end.  If the
     * application cannot do so, then the parser should report a fatal
     * error even if the XML 1.0 recommendation does not require it to
     * do so.</p>
     *
     * @param exception The error information encapsulated in a
     * SAX parse exception.
     * @throws SAXException Any SAX exception, possibly
     * wrapping another exception.
     */
    private boolean m_throwExceptionOnError = false;

    private List<Exception> exceptionList;

    public GatherErrorHandler() {
        exceptionList = new ArrayList<>();
    }

    public void error(SAXParseException exception) throws
                                                   SAXException {
        exceptionList.add(exception);
    }

    public List<Exception> getExceptionList() {
        return exceptionList;
    }
}
