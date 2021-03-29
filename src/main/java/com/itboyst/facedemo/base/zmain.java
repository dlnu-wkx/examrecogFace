package com.itboyst.facedemo.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class zmain
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// 设备购买及使用网址：https://item.taobao.com/item.htm?spm=a1z10.3-c.w4002-3280522495.9.58594b84tCw1Nf&id=40751680668
		/**
		 * IP地址 192.168.1.100 网络参数可以通过网络进行配置 <br/>
		 * 默认网关 192.168.1.1<br/>
		 * 子网掩码 255.255.255.0<br/>
		 * TCP端口 6722<br/>
		 * UDP端口 6723<br/>
		 */
		try {
			// 1:创建客户端的套接字
			Socket s = new Socket("192.168.1.100", 6722);
			// 2:获取输出流
			// 第1层：字符缓冲输出流
			// 第2层：字符转换输出流
			// 第3层：字节输出流
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			// 3 获取输入流
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			// 3:写数据
			// 字符串模式发送11打开第一路继电器，
			// 发送21关闭第一路继电器，
			// 11*指令第一路继电器点动
			// 11:10第一路打开10秒自动关闭（延时支持1-65535秒）
			// 发送00返回全部通道状态
			bw.write("16");
			bw.flush();
			// bw.close(); //切记，缓冲流写数据，需要刷空！！！

			// 告诉服务器。客户端这边数据写入完毕
			s.shutdownOutput();

			// 4:读取从服务器响应的数据
			String info = br.readLine(); // 阻塞式方式
			System.out.println(info);

			// 4:关闭套接字
			s.close();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
