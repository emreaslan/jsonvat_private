package com.jsonvat.exam.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsonvat.exam.model.Root;

public class DataParser {
	private URL url;

	public DataParser(URL url) {
		this.url = url;
	}

	public Root parseRoot() {
		ObjectMapper mapper = new ObjectMapper();
		JsonParser jsonInput = null;
		try {
			jsonInput = new JsonFactory().createParser(new FileReader(new File(this.url.getPath())));
			Root rootNode = mapper.readValue(jsonInput, Root.class);
			return rootNode;
		} catch (JsonParseException e) {
			e.printStackTrace();
			return null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
