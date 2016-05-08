package com.baidu.ee.msg;

import com.baidu.ee.msg.vo.MsgVo;

/**
 * ∂Ã–≈œ˚œ¢
 * @author Jord
 *
 */
public class SmsMsg extends BaseMsg implements IMsg {
	
	public SmsMsg() {
		this.msgType = "SMS";
	}
	public SmsMsg(MsgVo vo){
		super(vo);
		this.msgType = "SMS";
	}

	@Override
	public boolean sendMsg() {
		System.out.println(this.toString());
		return true;
	}

}
