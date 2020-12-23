package vc.thinker.cabbage.user;

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
//		JdbcConfig jdbc = new JdbcConfig(
//				"jdbc:mysql://epower001.mysql.rds.aliyuncs.com/nomo?useUnicode=true&characterEncoding=utf8",
//				"epower", "epower",
//				"com.mysql.jdbc.Driver");
		
//		String itemPath = "/Users/james/git/nomo/nomo/dal/src/main/java";
//		String itemPath = "D:\workspace\cabbage2.1\cabbage/dal/src/main/java";
//		String itemPath = "D:/workspace/cabbage2.1/cabbage/dal/src/main/java";
		
		String itemPath = "/Users/zhanggaoxiang/git/flash-web/dal/src/main/java";
		
		String rootPackage = "vc.thinker.cabbage.user";

		MyBatisGeneratorTool2 tool = new MyBatisGeneratorTool2(jdbc, itemPath, rootPackage);
		tool.setGeneratorBO(false);
		tool.setGeneratorDao(false);
		tool.setGeneratorMapperJava(false);
		
		List<GeneratorTable> tableList=Lists.newArrayList(
//				new GeneratorTable("creeper", "user_money_log", "UserMoneyLog"),
//				new GeneratorTable("creeper", "user_money", "UserMoney")
//				new GeneratorTable("cabbage-2.1.0", "user_vip_pay_log", "VipPayLog")
//				new GeneratorTable("creeper", "user_referee", "Referee")
//				new GeneratorTable("cabbage-2.1.0", "user_member", "Member")
//				new GeneratorTable("cabbage-2.1.0", "user_membership_card", "MembershipCard")
				new GeneratorTable("radish", "user_money_recharge", "UserMoneyRecharge")
//				new GeneratorTable("cabbage-2.1.0", "user_seller", "Seller")
//				new GeneratorTable("cabbage-2.1.0", "user_pay_amount", "PayAmount")
//				new GeneratorTable("radish", "user_repairer", "Repairer"),
//				new GeneratorTable("radish", "user_track_point", "TrackPoint")
//				new GeneratorTable("cabbage-2.1.0", "user_coupon", "UserCoupon")
//				new GeneratorTable("nomo", "user_deposit_pay_log", "DepositPayLog")
//				new GeneratorTable("cabbage-2.1.0", "user_integral_log", "IntegralLog")
//				new GeneratorTable("cabbage-2.1.0", "user_integral_rule", "IntegralRule")
//				new GeneratorTable("cabbage-2.1.0", "user_repairer", "Repairer")
//				new GeneratorTable("cabbage-2.1.0", "user_seller", "Seller")
//				new GeneratorTable("cabbage-2.1.0", "se_cabinet", "Cabinet")
//				new GeneratorTable("cabbage-2.1.0", "user_money_cash", "UserMoneyCash")
//				new GeneratorTable("cabbage-2.1.0", "user_rebate_money_log", "UserRebateMoneyLog")
//				new GeneratorTable("cabbage-2.1.0", "user_basic_cost_pay_log", "UserBasicCostPayLog")
				);

		tool.generator(tableList);
		
		System.out.println("生成成功");
	}
	
}
