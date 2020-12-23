package vc.thinker.apiv2.http.response;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;

import com.sinco.data.core.Page;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PageResponse<T> extends AbstractResponse<T> {
	
	public PageResponse(){
	}
	
	public PageResponse(MessageSource messageSource,HttpServletRequest request){
		super(messageSource,request);
	}

	private List<T> content;

	private Boolean first;

	private Boolean last;

	private Integer number;

	private Integer size;

	private Long totalElements;

	private Integer totalPages;

	private Date searchDate = new Date();

	@SuppressWarnings("rawtypes")
	public void init(Page page, List<T> content) {
		this.content = content;
		first = page.isFirstPage();
		last = page.isLastPage();
		number = page.getPageNumber();
		size = page.getPageSize();
		totalElements = page.getTotalElements();
		totalPages = page.getTotalPages();
	}

	@SuppressWarnings("rawtypes")
	public void init(org.springframework.data.domain.Page page, List<T> content) {
		this.content = content;
		first = page.isFirst();
		last = page.isLast();
		number = page.getNumber() + 1;
		size = page.getSize();
		totalElements = page.getTotalElements();
		totalPages = page.getTotalPages();
	}
}
