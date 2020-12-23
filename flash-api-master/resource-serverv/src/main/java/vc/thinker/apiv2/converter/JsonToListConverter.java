package vc.thinker.apiv2.converter;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerConverter;

import com.alibaba.fastjson.JSON;

public class JsonToListConverter extends DozerConverter<String, List> {

	public JsonToListConverter() {
		super(String.class, List.class);
	}

	@Override
	public List convertTo(String source, List destination) {
		if(StringUtils.isBlank(source)){
			return null;
		}
		return JSON.parseObject(source, List.class);
	}

	@Override
	public String convertFrom(List source, String destination) {
		if(source == null){
			return null;
		}
		return JSON.toJSONString(source);
	}

}
