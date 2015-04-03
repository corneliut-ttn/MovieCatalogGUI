package moviecatalog.xml;

import moviecatalog.model.MovieCatalog;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Cornelius on 03.04.2015.
 * The JAXBContext class provides the client's entry point to the JAXB API.
 * It provides an abstraction for managing the XML/Java binding information necessary to implement the
 * JAXB binding framework operations: unmarshal, marshal and validate
 *
 * The Marshaller class provides the client application the ability to convert a Java content tree back into XML data.
 */
public class JavaToXML {

    private File file=new File("./src/moviecatalog/xml/data.xml");

    public void exportToXML(MovieCatalog object )throws JAXBException{
        JAXBContext context = JAXBContext.newInstance(MovieCatalog.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(object, file);
    }

    public MovieCatalog importToJava()throws JAXBException{
        JAXBContext context = JAXBContext.newInstance(MovieCatalog.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = unmarshaller.unmarshal( file );
        return (MovieCatalog)object;
    }
}
