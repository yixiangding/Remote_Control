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
			//����serversocket����ָ���˿ڣ�1024-65535��
			ServerSocket serverSocket = new ServerSocket(10000);
			//�����̣߳���������Ӧ�˿ڡ����ͻ�������ʱ���ؿͻ���socket
			Socket socket = serverSocket.accept();
			print ("connected!\n");
			//Socket socket = new Socket();
			//��ȡ����������ȡ�ͻ�������
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String info;
			while (true) {
				info = br.readLine();
				if (info == null) break;
				print (info + "\n");
			}
			socket.shutdownInput();
			print("read over!\n");
			//��ȡ���������Ӧ�ͻ���
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			pw.write("Message received!");
			pw.flush();
			
			//�ر���Դ
			pw.close();
			br.close();
			socket.close();
			serverSocket.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
}
