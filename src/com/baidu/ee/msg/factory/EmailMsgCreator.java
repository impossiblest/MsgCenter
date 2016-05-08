package com.baidu.ee.msg.factory;

import com.baidu.ee.msg.EmailMsg;
import com.baidu.ee.msg.IMsg;
import com.baidu.ee.msg.vo.MsgVo;

/**
 * 创建邮箱的工厂
 * @author Jord
 *
 */
public class EmailMsgCreator implements IMsgFactory {
	private static EmailMsgCreator singleton;
	private EmailMsgCreator(){}
	public static EmailMsgCreator getInstance(){
		if(singleton==null){
			synchronized(EmailMsgCreator.class){
				if(singleton==null){
					singleton = new EmailMsgCreator();
				}
			}
		}
		return singleton;
	}

	@Override
	public IMsg create(MsgVo vo) {
		return new EmailMsg(vo);
	}
	
	
}
