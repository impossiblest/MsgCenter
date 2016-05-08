package com.baidu.ee.msg.factory;

import com.baidu.ee.msg.IMsg;
import com.baidu.ee.msg.vo.MsgVo;

/**
 * 消息工厂类
 * @author Jord
 *
 */
public interface IMsgFactory {
	/**
	 * 返回消息接口
	 * @return IMsg
	 */
	public IMsg create(MsgVo vo);
}
