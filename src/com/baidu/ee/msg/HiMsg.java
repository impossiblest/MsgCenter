package com.baidu.ee.msg;

import com.baidu.ee.msg.vo.MsgVo;
/**
 * �ٶ�Hi��Ϣ
 * @author Jord
 *
 */
public class HiMsg extends BaseMsg implements IMsg {
	
	public HiMsg(){
		this.msgType = "Hi";
	}
	
	public HiMsg(MsgVo vo) {
		super(vo);
		this.msgType = "Hi";
	}
	

	@Override
	public boolean sendMsg() {
		System.out.println(this.toString());
		return true;
	}

}
