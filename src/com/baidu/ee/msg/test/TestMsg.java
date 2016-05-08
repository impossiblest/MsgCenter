package com.baidu.ee.msg.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.baidu.ee.msg.IMsg;
import com.baidu.ee.msg.factory.EmailMsgCreator;
import com.baidu.ee.msg.factory.MsgFactory;
import com.baidu.ee.msg.vo.MsgVo;

/**
 * 测试
 * @author Jord
 *
 */
public class TestMsg {
	public static void main(String[] args) {
//		MsgVo vo  = new MsgVo();
//		vo.setFrom("Jord");
//		vo.setTo("Susan");
//		vo.setContent("Nice to meet u.How U doing...");
//		//TODO 工厂方法模式可以考虑用动态代理 代替
//		IMsg msg = null;
//		msg = 	EmailMsgCreator.getInstance().create(vo);
//		msg = MsgFactory.getInstance("HiMsg", vo);
//		msg.sendMsg();
//		System.out.println("simplename:"+msg.getClass().getSimpleName());
//		final BlockingQueue queue = new ArrayBlockingQueue(3);
//		for(int i=0;i<2;i++){
//			new Thread(){
//				public void run(){
//					while(true){
//						try {
//							Thread.sleep((long) (Math.random()*1000));
//							System.out.println(Thread.currentThread().getName() + "准备放数据!");                                                      queue.put(1);
//							queue.put(1);
//							System.out.println(Thread.currentThread().getName() + "已经放了数据，" +               
//							"队列目前有" + queue.size() + "个数据");
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				}
//			}.start();
//			
//			new Thread(){
//				public void run(){
//					while(true){
//						try {
//							Thread.sleep(100);
//							System.out.println(Thread.currentThread().getName() + "准备取数据!");                                                      queue.put(1);
//							queue.take();
//							System.out.println(Thread.currentThread().getName() + "已经取走数据，" +               
//							"队列目前有" + queue.size() + "个数据");
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				}
//			}.start();
//		}
	}

}
