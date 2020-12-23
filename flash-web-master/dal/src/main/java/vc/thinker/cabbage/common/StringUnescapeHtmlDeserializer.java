package vc.thinker.cabbage.common;

import java.io.IOException;

import org.apache.commons.lang3.StringEscapeUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * 用于反转html
 * @author james
 *
 */
public class StringUnescapeHtmlDeserializer extends JsonDeserializer<String>{

	public StringUnescapeHtmlDeserializer(Class<String> string) {
        super();
    }
	
    public Class<String> handledType() {
        return String.class;
    }
	
	@Override
	public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return StringEscapeUtils.unescapeHtml4(p.getText());
	}

}
