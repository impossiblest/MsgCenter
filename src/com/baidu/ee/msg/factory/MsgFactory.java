package com.baidu.ee.msg.factory;

import java.lang.reflect.Constructor;

import com.baidu.ee.msg.HiMsg;
import com.baidu.ee.msg.IMsg;
import com.baidu.ee.msg.vo.MsgVo;

/**
 * ��Ϣ�򵥹�����<br>
 * ������Ϣ������,���÷��䷵����Ӧʵ��;
 * �������쳣�򷵻�Ĭ�ϵ�Hi��Ϣ��
 * @author Jord
 *
 */
public class MsgFactory {
	private static String DEFAULT_PACKAGE = "com.baidu.ee.msg.";
	
	public static IMsg getInstance(String name,MsgVo vo){
		IMsg msg = null;
		Class<?> demo = null;
		try {
			demo = Class.forName(DEFAULT_PACKAGE+name);
			Class<?>[] paramTypes = { MsgVo.class};
			Object[] params = {vo}; // ��������Ĳ���
			Constructor<?> con = demo.getConstructor(paramTypes); //��Ҫ���������
			msg = (IMsg)con.newInstance(params);
		} catch (Exception e) {
			msg = new HiMsg(vo);
		}
		return msg;
	}
}
