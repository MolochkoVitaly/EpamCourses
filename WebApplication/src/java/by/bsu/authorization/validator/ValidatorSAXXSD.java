package by.bsu.authorization.validator;


import by.bsu.authorization.handler.DepositErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ValidatorSAXXSD {
    public static void validate() {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        String fileName = "xml/deposits.xml";
        String schemaName = "xml/deposit.xsd";
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            DepositErrorHandler handler = new DepositErrorHandler();
            validator.setErrorHandler(handler);
            validator.validate(source);
            System.out.println(fileName + " is valid.");
        } catch (SAXException e) {
            System.err.print("validation "+ fileName + " is not valid because " + e.getMessage());
        } catch (IOException e) {
            System.err.print("validation " + fileName + " is not valid because " + e.getMessage());
        }
    }
}
