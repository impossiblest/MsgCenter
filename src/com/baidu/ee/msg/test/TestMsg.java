package com.baidu.ee.msg.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.baidu.ee.msg.IMsg;
import com.baidu.ee.msg.factory.EmailMsgCreator;
import com.baidu.ee.msg.factory.MsgFactory;
import com.baidu.ee.msg.vo.MsgVo;

/**
 * ����
 * @author Jord
 *
 */
public class TestMsg {
	public static void main(String[] args) {
//		MsgVo vo  = new MsgVo();
//		vo.setFrom("Jord");
//		vo.setTo("Susan");
//		vo.setContent("Nice to meet u.How U doing...");
//		//TODO ��������ģʽ���Կ����ö�̬���� ����
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
//							System.out.println(Thread.currentThread().getName() + "׼��������!");                                                      queue.put(1);
//							queue.put(1);
//							System.out.println(Thread.currentThread().getName() + "�Ѿ��������ݣ�" +               
//							"����Ŀǰ��" + queue.size() + "������");
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
//							System.out.println(Thread.currentThread().getName() + "׼��ȡ����!");                                                      queue.put(1);
//							queue.take();
//							System.out.println(Thread.currentThread().getName() + "�Ѿ�ȡ�����ݣ�" +               
//							"����Ŀǰ��" + queue.size() + "������");
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
