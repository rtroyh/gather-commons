package com.gather.gathercommons.xml.krypto;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.util.Collections;
import java.util.List;

/**
 * This class is used to provide convenient methods to digitally sign an XML
 * document.
 *
 * @author <a href="mailto:debadatta.mishra@gmail.com">Debadatta Mishra</a>
 * @since 2013
 */
public class XmlDigitalSignatureGenerator {

    /**
     * Method used to get the XML document by parsing
     *
     * @param xmlFilePath , file path of the XML document
     * @return Document
     */
    private Document getXmlDocument(String xmlFilePath) {
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);

        try {
            doc = dbf.newDocumentBuilder().parse(new FileInputStream(xmlFilePath));
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return doc;
    }

    /**
     * Method used to get the KeyInfo
     *
     * @param xmlSigFactory
     * @param publicKeyPath
     * @return KeyInfo
     */
    private KeyInfo getKeyInfo(XMLSignatureFactory xmlSigFactory,
                               String publicKeyPath) {
        KeyInfo keyInfo = null;
        KeyValue keyValue = null;
        PublicKey publicKey = new KryptoUtil().getStoredPublicKey(publicKeyPath);
        KeyInfoFactory keyInfoFact = xmlSigFactory.getKeyInfoFactory();

        try {
            keyValue = keyInfoFact.newKeyValue(publicKey);
        } catch (KeyException ex) {
            ex.printStackTrace();
        }

        keyInfo = keyInfoFact.newKeyInfo(Collections.singletonList(keyValue));
        return keyInfo;
    }

    /*
     * Method used to store the signed XMl document
     */
    private void storeSignedDoc(Document doc,
                                String destnSignedXmlFilePath) {
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer trans = null;

        try {
            trans = transFactory.newTransformer();
        } catch (TransformerConfigurationException ex) {
            ex.printStackTrace();
        }

        try {
            StreamResult streamRes = new StreamResult(new File(destnSignedXmlFilePath));
            if (trans != null) {
                trans.transform(new DOMSource(doc),
                                streamRes);
            }
        } catch (TransformerException ex) {
            ex.printStackTrace();
        }

        System.out.println("XML file with attached digital signature generated successfully ...");
    }

    /**
     * Method used to attach a generated digital signature to the existing
     * document
     *
     * @param originalXmlFilePath
     * @param destnSignedXmlFilePath
     * @param privateKeyFilePath
     * @param publicKeyFilePath
     */
    public void generateXMLDigitalSignature(String originalXmlFilePath,
                                            String destnSignedXmlFilePath,
                                            String privateKeyFilePath,
                                            String publicKeyFilePath) {
        //Get the XML Document object
        Document doc = getXmlDocument(originalXmlFilePath);
        //Create XML Signature Factory
        XMLSignatureFactory xmlSigFactory = XMLSignatureFactory.getInstance("DOM");
        PrivateKey privateKey = new KryptoUtil().getStoredPrivateKey(privateKeyFilePath);
        DOMSignContext domSignCtx = new DOMSignContext(privateKey,
                                                       doc.getDocumentElement());
        Reference ref = null;
        SignedInfo signedInfo = null;


        try {
            final DigestMethod digestMethod = xmlSigFactory.newDigestMethod(DigestMethod.SHA256,
                                                                            null);
            final CanonicalizationMethod canonicalizationMethod = xmlSigFactory.newCanonicalizationMethod("http://www.w3.org/2001/10/xml-exc-c14n#",
                                                                                                          (C14NMethodParameterSpec) null);
            final SignatureMethod signatureMethod = xmlSigFactory.newSignatureMethod(SignatureMethod.RSA_SHA1,
                                                                                     null);

            final Transform transform = xmlSigFactory.newTransform(Transform.ENVELOPED,
                                                                   (TransformParameterSpec) null);
            final List<Transform> transforms = Collections.singletonList(transform);

            ref = xmlSigFactory.newReference("",
                                             digestMethod,
                                             transforms,
                                             null,
                                             null);

            final List<Reference> references = Collections.singletonList(ref);

            signedInfo = xmlSigFactory.newSignedInfo(canonicalizationMethod,
                                                     signatureMethod,
                                                     references);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } catch (InvalidAlgorithmParameterException ex) {
            ex.printStackTrace();
        }
        //Pass the Public Key File Path 
        KeyInfo keyInfo = getKeyInfo(xmlSigFactory,
                                     publicKeyFilePath);
        //Create a new XML Signature
        XMLSignature xmlSignature = xmlSigFactory.newXMLSignature(signedInfo,
                                                                  keyInfo);
        try {
            //Sign the document
            xmlSignature.sign(domSignCtx);
        } catch (MarshalException ex) {
            ex.printStackTrace();
        } catch (XMLSignatureException ex) {
            ex.printStackTrace();
        }
        //Store the digitally signed document inta a location
        storeSignedDoc(doc,
                       destnSignedXmlFilePath);
    }
}
