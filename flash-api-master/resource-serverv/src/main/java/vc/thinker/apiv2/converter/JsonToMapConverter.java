package vc.thinker.apiv2.converter;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerConverter;

import com.alibaba.fastjson.JSON;

public class JsonToMapConverter extends DozerConverter<String, Map> {

	public JsonToMapConverter() {
		super(String.class,Map.class);
	}

	@Override
	public Map convertTo(String source, Map destination) {
		if(StringUtils.isBlank(source)){
			return null;
		}
		return JSON.parseObject(source, Map.class);
	}

	@Override
	public String convertFrom(Map source, String destination) {
		if(source == null){
			return null;
		}
		return JSON.toJSONString(source);
	}
}
