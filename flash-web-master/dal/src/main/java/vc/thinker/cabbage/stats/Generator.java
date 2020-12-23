package vc.thinker.cabbage.stats;


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
				"jdbc:mysql://epower001.mysql.rds.aliyuncs.com/nomo?useUnicode=true&characterEncoding=utf8",
				"epower", "epower",
				"com.mysql.jdbc.Driver");
		
//		String itemPath = "/Users/james/git/sinco-cabbage/sinco-cabbage/dal/src/main/java";
		
		String itemPath = "/E:/radish-3/nomo/dal/src/main/java";
		
		String rootPackage = "vc.thinker.cabbage.stats";

		MyBatisGeneratorTool2 tool = new MyBatisGeneratorTool2(jdbc, itemPath, rootPackage);
		tool.setGeneratorBO(false);
		tool.setGeneratorDao(false);
		tool.setGeneratorMapperJava(false);
		
		List<GeneratorTable> tableList=Lists.newArrayList(
//				new GeneratorTable("cabbage-2.1.0", "st_deposit_stats", "DepositStats")
//				new GeneratorTable("cabbage-2.1.0", "st_register_stats", "RegisterStats")
//				new GeneratorTable("cabbage-2.1.0", "st_vip_stats", "VipStats"),
//				new GeneratorTable("cabbage-2.1.0", "se_order_stats", "OrderStats")
//				new GeneratorTable("cabbage-2.1.0", "st_balance_stats", "BalanceStats")
//				new GeneratorTable("cabbage-2.1.0", "st_stats", "DayStats")
//				new GeneratorTable("cabbage-2.1.0", "se_cabinet_stats", "CabinetStats")
//				new GeneratorTable("cabbage-2.1.0", "se_pb_stats", "PbStats")
				new GeneratorTable("cabbage-2.1.0", "st_income_stats", "IncomeStats")
		);

		tool.generator(tableList);
		System.out.println("结束！！！！");
	}
	
}
