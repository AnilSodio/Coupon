package vc.thinker.apiv2.common;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * 用于防止xss攻击
 * @author james
 *
 */
public class HttpXssObjectMapper extends ObjectMapper{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3459228577074900479L;

	public HttpXssObjectMapper() {
		
        SimpleModule module = new SimpleModule("HTML XSS Serializer",
                new Version(1, 0, 0, "FINAL","com.yihaomen","ep-jsonmodule"));
        
        module.addSerializer(new JsonHtmlUnescapeSerializer(String.class));
        
        module.addDeserializer(String.class, new JsonHtmlEscapeDeserializer(String.class));
        
        this.registerModule(module);
	 }
	
}
