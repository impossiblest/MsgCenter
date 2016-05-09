package com.baidu.ee.msg.factory;

import java.lang.reflect.Constructor;

import com.baidu.ee.msg.HiMsg;
import com.baidu.ee.msg.IMsg;
import com.baidu.ee.msg.vo.MsgVo;

/**
 * 消息简单工厂类<br>
 * 根据消息的类型,利用反射返回相应实例;
 * 若产生异常则返回默认的Hi消息。
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
			Object[] params = {vo}; // 方法传入的参数
			Constructor<?> con = demo.getConstructor(paramTypes); //主要就是这句了
			msg = (IMsg)con.newInstance(params);
		} catch (Exception e) {
			msg = new HiMsg(vo);
		}
		return msg;
	}
}
