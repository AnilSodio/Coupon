package vc.thinker.apiv2.common;

import org.apache.commons.lang3.StringEscapeUtils;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;

public class HTMLCharacterEscapes extends CharacterEscapes
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3055163275069871138L;
	
	
	private final int[] asciiEscapes;
    
    public HTMLCharacterEscapes()
    {
        asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
        // and force escaping of a few others:
        asciiEscapes['<'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['>'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['&'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['"'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['\''] = CharacterEscapes.ESCAPE_CUSTOM;
    }
    
    @Override
    public int[] getEscapeCodesForAscii() {
        return asciiEscapes;
    }
    
    // and this for others; we don't need anything special here
    @Override public SerializableString getEscapeSequence(int ch) {
    	String c=StringEscapeUtils.unescapeHtml4(Character.toString((char) ch));
    	return new SerializedString(c);
    }
}
