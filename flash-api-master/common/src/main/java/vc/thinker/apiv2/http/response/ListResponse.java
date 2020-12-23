package vc.thinker.apiv2.http.response;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ListResponse<T> extends AbstractResponse<T> {
	
	public ListResponse(){
	}
	
	public ListResponse(MessageSource messageSource,HttpServletRequest request){
		super(messageSource,request);
	}
	
	private List<T> items;
}
