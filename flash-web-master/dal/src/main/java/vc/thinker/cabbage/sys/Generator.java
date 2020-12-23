package vc.thinker.cabbage.sys;


import java.util.List;

import com.google.common.collect.Lists;
import com.sinco.mybatis.generator.GeneratorTable;
import com.sinco.mybatis.generator.MyBatisGeneratorTool2;
import com.sinco.mybatis.generator.config.JdbcConfig;

/**
 * 代码自动产生
 * @author james
 *
 */
public class Generator {

	public static void main(String[] args) {
		
		//自动产生代码生成改造 by james
		JdbcConfig jdbc = new JdbcConfig(
				"jdbc:mysql://192.168.1.250:3306/nomo-saas-1.0.x?useUnicode=true&characterEncoding=utf8",
				"root", "admin123",
				"com.mysql.jdbc.Driver");
		
//		String itemPath = "/Users/james/git/nomo/nomo/dal/src/main/java";
		
		String itemPath = "/E:/radish-3/nomo/dal/src/main/java";
		
//		String itemPath = "/Users/linanzhi/git/nomo/dal/src/main/java";
		
		String rootPackage = "vc.thinker.cabbage.sys";

		MyBatisGeneratorTool2 tool = new MyBatisGeneratorTool2(jdbc, itemPath, rootPackage);
		tool.setGeneratorBO(false);
		tool.setGeneratorDao(false);
		tool.setGeneratorMapperJava(false);
		
		List<GeneratorTable> tableList=Lists.newArrayList(
//				new GeneratorTable("cabbage-2.1.0", "sys_coupon", "Coupon")
//				new GeneratorTable("cabbage-2.1.0", "sys_platform_revenue", "PlatformRevenue")
//				new GeneratorTable("cabbage-2.1.0", "sys_exchange_rate", "ExchangeRate")
//				new GeneratorTable("cabbage-2.1.0", "sys_promotion", "Promotion")
//				new GeneratorTable("cabbage-2.1.0", "sys_promotion_type", "PromotionType")
//				new GeneratorTable("cabbage-2.1.0", "sys_resource", "Resource")
				new GeneratorTable("cabbage-2.1.0", "sys_country", "Country")
//				new GeneratorTable("cabbage-2.1.0", "sys_user_guide", "UserGuide")
		);

		tool.generator(tableList);
		
		System.out.println("恭喜生成完成！！！");
	}
	
}
