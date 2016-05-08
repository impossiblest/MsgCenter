package com.baidu.ee.msg.factory;

import com.baidu.ee.msg.IMsg;
import com.baidu.ee.msg.vo.MsgVo;

/**
 * ��Ϣ������
 * @author Jord
 *
 */
public interface IMsgFactory {
	/**
	 * ������Ϣ�ӿ�
	 * @return IMsg
	 */
	public IMsg create(MsgVo vo);
}
