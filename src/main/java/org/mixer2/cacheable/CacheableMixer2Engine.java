package org.mixer2.cacheable;

import java.io.StringReader;

import javax.xml.bind.JAXBException;

import org.mixer2.Mixer2Engine;
import org.mixer2.jaxb.xhtml.Html;

public class CacheableMixer2Engine extends Mixer2Engine {

    @Override
    protected Html unmarshal(StringBuilder sb) throws JAXBException {
        Html html = null;
        sb = removeDoctypeDeclaration(sb);
        sb = replaceNamedEntity(sb);
        StringReader stringReader = new StringReader(sb.toString());
        html = (Html) getJAXBContext().createUnmarshaller().unmarshal(stringReader);
        return html;
    }
    
}
