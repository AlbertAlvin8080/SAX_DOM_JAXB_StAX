package sax.questao_10;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandlerJ {

	public static void main(String[] args) {
		DefaultHandler handler = new DefaultHandler() {
			private Set<String> artists = new HashSet<>();
			private boolean bArtist;
			
			@Override
			public void startElement(String uri, String localName, String qName, Attributes attributes)
					throws SAXException {
				if(qName.equals("artist")) {
					bArtist = true;
				}
			}
			
			@Override
			public void characters(char[] ch, int start, int length) throws SAXException {
				if(bArtist) {
					bArtist = false;
					artists.add(new String(ch, start, length));
				}
			}
			
			@Override
			public void endDocument() throws SAXException {
				System.out.printf("Quantos artistas distintos existem no catálogo? : (%d) -> %s", artists.size(), artists);
			}
		};
		
		File file = new File("./src/main/resources/cd_catalog.xml");

		try {
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			parser.parse(file, handler);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

}
