package com.baidu.ee.msg.factory;

import com.baidu.ee.msg.HiMsg;
import com.baidu.ee.msg.IMsg;
import com.baidu.ee.msg.vo.MsgVo;

/**
 * 创建Hi的工厂
 * @author Jord
 *
 */
public class HiMsgCreator implements IMsgFactory {
	private static HiMsgCreator singleton;
	private HiMsgCreator(){}
	public static HiMsgCreator getInstance(){
		if(singleton==null){
			synchronized(HiMsgCreator.class){
				if(singleton==null){
					singleton = new HiMsgCreator();
				}
			}
		}
		return singleton;
	}
	@Override
	public IMsg create(MsgVo vo) {
		return new HiMsg(vo);
	}

	

}
