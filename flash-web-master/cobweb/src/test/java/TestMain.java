import java.util.Date;

import com.alibaba.fastjson.JSON;

import vc.thinker.cabbage.se.CabinetRemoteHandle.CabinetNoticeContent;
import vc.thinker.cabbage.se.CabinetRemoteHandle.NoticeType;

public class TestMain {

	public static void main(String[] args) {
		CabinetNoticeContent content = new CabinetNoticeContent();
		content.setChannel(6);
		content.setCabinetId("1");
		content.setNoticeTime(new Date());
		content.setNoticeType(NoticeType.sys_out);
		System.out.println(JSON.toJSONString(content));
	}
}
