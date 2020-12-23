//package vc.thinker.cabbage.test;
//
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import vc.thinker.cabbage.se.OrderRebateService;
//import vc.thinker.cabbage.se.model.Order;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = SpringJUnitTestApplication.class)
//public class UmOrderServiceTest {
//
//	@InjectMocks
////	@Autowired
//	private OrderRebateService orderRebateService;
//
//	// 在@Test标注的测试方法之前运行
//	@Before
//	public void setUp() throws Exception {
//		// 初始化测试用例类中由Mockito的注解标注的所有模拟对象
//		MockitoAnnotations.initMocks(this);
//	}
//	
//	@Test
//	public void testRebate(){
//		Order order = new Order();
//		orderRebateService.rebate(order);
//	}
//}
