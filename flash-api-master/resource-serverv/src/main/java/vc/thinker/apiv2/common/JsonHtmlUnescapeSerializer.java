package vc.thinker.apiv2.common;

import java.io.IOException;

import org.apache.commons.lang3.StringEscapeUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonHtmlUnescapeSerializer extends JsonSerializer<String> {

	public JsonHtmlUnescapeSerializer(Class<String> string) {
        super();
    }
    @Override
	public Class<String> handledType() {
        return String.class;
    }
	
	@Override
	public void serialize(String value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		if (value != null) {
	         String encodedValue = StringEscapeUtils.unescapeHtml4(value);
	         gen.writeString(encodedValue);
	      }
	}
}
