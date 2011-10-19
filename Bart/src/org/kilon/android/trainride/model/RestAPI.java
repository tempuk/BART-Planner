package org.kilon.android.trainride.model;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import android.util.Log;

public abstract class RestAPI {
	
	protected final String url;
	
	public RestAPI(String url) {
		this.url = url;
	}
	
	public final Document fetch() throws ParserConfigurationException, SAXException, IOException {
		Log.i("RestAPI", "Fetching " + url);
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		return builder.parse(url);
	}
	
	public String getUrl() {
		return url;
	}

}
