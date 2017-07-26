package theHost;
import acm.program.*;
import java.awt.*;
import java.io.*;
import java.net.*;

import javax.swing.*;

public class theHost_main extends ConsoleProgram{
	public void init() {
		setSize(1000, 600);
		add(new JLabel("test            "), WEST);
		add(new JLabel(" "), WEST);
		add(new JLabel("test 2"), WEST);
	}
	
	public void run() {
		print ("start running!\n");
		
		try {
			//创建serversocket监听指定端口（1024-65535）
			ServerSocket serverSocket = new ServerSocket(10000);
			//堵塞线程，并监听对应端口。当客户端连接时返回客户端socket
			Socket socket = serverSocket.accept();
			print ("connected!\n");
			//Socket socket = new Socket();
			//获取输入流，读取客户端输入
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String info;
			while (true) {
				info = br.readLine();
				if (info == null) break;
				print (info + "\n");
			}
			socket.shutdownInput();
			print("read over!\n");
			//获取输出流，响应客户端
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			pw.write("Message received!");
			pw.flush();
			
			//关闭资源
			pw.close();
			br.close();
			socket.close();
			serverSocket.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
}
