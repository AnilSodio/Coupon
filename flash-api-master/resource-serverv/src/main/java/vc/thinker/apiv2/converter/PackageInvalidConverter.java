package vc.thinker.apiv2.converter;

import java.util.Date;

import org.dozer.DozerConverter;

/**
 * 套餐是否失效的转换
 * @author james
 *
 */
public class PackageInvalidConverter extends DozerConverter<Date,Boolean> {

	public PackageInvalidConverter() {
		super(Date.class,Boolean.class);
	}
	
	@Override
	public Boolean convertTo(Date source, Boolean destination) {
		if (source != null && source.getTime() >= new Date().getTime()) 
		{
			return false;
		}
		return true;
	}

	@Override
	public Date convertFrom(Boolean source, Date destination) {
		return null;
	}

}
