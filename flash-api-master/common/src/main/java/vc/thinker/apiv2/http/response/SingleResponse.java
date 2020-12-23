package vc.thinker.apiv2.http.response;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SingleResponse<T> extends AbstractResponse<T> {
	
	public SingleResponse(){
	}
	
	public SingleResponse(MessageSource messageSource,HttpServletRequest request){
		super(messageSource,request);
	}
	
	private T item;
}
