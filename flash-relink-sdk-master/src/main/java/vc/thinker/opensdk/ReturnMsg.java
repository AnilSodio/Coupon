package vc.thinker.opensdk;

import java.util.ArrayList;
import java.util.List;

import vc.thinker.opensdk.powerbank.relink.RelinkPowerbank;
import vc.thinker.opensdk.powerbank.relink.RelinkSlot;

import com.alibaba.fastjson.JSON;

/**
 * 统一定义返回信息类
 * @author wangdawei
 *
 * @param <T>
 */
public class ReturnMsg<T> {
	
	private int retCode;
	
	private String retMsg;
	
	private T data;
	
	public ReturnMsg() {
		this.retCode = ReturnCode.IOT_PAAS_EXCEPTION.getRetCode();
		this.retMsg = ReturnCode.IOT_PAAS_EXCEPTION.getRetMsg();
	}
	
	
	public ReturnMsg(T data) {
		this.retCode = 0;
		this.retMsg = "成功";
		this.data = data;
	}
	
	public ReturnMsg(ReturnCode cm) {
		this.retCode = cm.getRetCode();
		this.retMsg = cm.getRetMsg();
	}
	
	public ReturnMsg(ReturnCode cm, T data) {
		this.retCode = cm.getRetCode();
		this.retMsg = cm.getRetMsg();
		this.data = data;
	}
	
	public ReturnMsg(int retCode, String retMsg, T data) {
		this.retCode = retCode;
		this.retMsg = retMsg;
		this.data = data;
	}


	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getRetCode() {
		return retCode;
	}

	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	@Override
	public String toString(){	
		return JSON.toJSONString(this);	
	}
	
	
	public static void main(String[] args) {
		
		RelinkPowerbank pb = new RelinkPowerbank();
		
		List<RelinkSlot> slots = new ArrayList<RelinkSlot>();
		for (int i = 0; i < 8; i++) {
			RelinkSlot slot = new RelinkSlot();
			slot.setSnn("TLt3625332"+i);
			slot.setNo(i);
			slot.setEq(4);
			
			slots.add(slot);
		}
		
		
		pb.setSlots(slots);
		pb.setNum(8);
		pb.setSn("RL1H081904660012");
		ReturnMsg<RelinkPowerbank> ret = new ReturnMsg<RelinkPowerbank>();
		ret.setData(pb);
		ret.setRetCode(0);
		ret.setRetMsg("成功");
		
		
		System.err.println(ret.toString());
		
		
		List<RelinkPowerbank> pbs = new ArrayList<RelinkPowerbank>();
		pbs.add(pb);
		pbs.add(pb);
		ReturnMsg<List<RelinkPowerbank>> rets = new ReturnMsg<List<RelinkPowerbank>>();
		rets.setData(pbs);
		rets.setRetCode(0);
		rets.setRetMsg("成功");
		
		System.err.println(rets.toString());
		
		ReturnMsg<RelinkSlot> retslot = new ReturnMsg<RelinkSlot>();
		RelinkSlot slot = new RelinkSlot();
		slot.setSnn("TLt36253321");
		slot.setNo(1);
		slot.setEq(4);
		retslot.setData(slot);
		retslot.setRetCode(0);
		retslot.setRetMsg("成功");
		System.err.println(retslot.toString());
	}
	
}
