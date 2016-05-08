package com.baidu.ee.msg;

import com.baidu.ee.msg.vo.MsgVo;

/**
 * ÓÊ¼şÏûÏ¢
 * @author Jord
 *
 */
public class EmailMsg extends BaseMsg implements IMsg{
	
	public EmailMsg() {
		this.msgType = "EMAIL";
	}

	public EmailMsg(MsgVo vo) {
		super(vo);
		this.msgType = "EMAIL";
	}

	@Override
	public boolean sendMsg() {
		System.out.println(this.toString());
		return true;
	}

}
