package com.baidu.ee.msg;

import com.baidu.ee.msg.vo.MsgVo;
/**
 * 百度Hi消息
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
