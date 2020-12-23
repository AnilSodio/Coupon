package vc.thinker.cabbage.se;


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
		
		// Database configuration
		JdbcConfig jdbc = new JdbcConfig(
				"jdbc:mysql://192.168.1.250:3306/nomo-saas-1.0.x?useUnicode=true&characterEncoding=utf8",
				"root", "admin123",
				"com.mysql.jdbc.Driver");
		
		//Code store physical path
		String itemPath = "E:/radish-3/nomo/dal/src/main/java";
		
		//Code store package path
		String rootPackage = "vc.thinker.cabbage.se";

		// init tools
		MyBatisGeneratorTool2 tool = new MyBatisGeneratorTool2(jdbc, itemPath, rootPackage);
		tool.setGeneratorBO(false);
		tool.setGeneratorDao(false);
		tool.setGeneratorMapperJava(false);
		
		List<GeneratorTable> tableList=Lists.newArrayList(
				new GeneratorTable("cabbage-2.1.0", "se_order", "Order"),
				new GeneratorTable("cabbage-2.1.0", "se_feedback_type", "FeedbackType")
		);

		tool.generator(tableList);
		
		System.out.println("恭喜生成完成！！！");
	}
	
}
