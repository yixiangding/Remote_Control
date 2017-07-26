package theClient;
import acm.program.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class theClient extends ConsoleProgram{
	public void init() {
		print("initialized!\n");
	}
	
	public void run() {
		String theHost = "192.168.191.1";
		try {
			Socket socket = new Socket(theHost, 10000);
			print("connected!\n");
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			pw.println("client: Hello!");
//			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			String info;
//			while (true) {
//				info = br.readLine();
//				if (info == null) break;
//				print(info + "\n");
//			}
			while(true) {
				String str = readLine("send?(y/n)");
				if (str.equals("y") || str.equals("")) break;
			};
			BufferedReader reader = new BufferedReader(new FileReader("gcode.nc"));
			String gcode = reader.readLine();
			while (gcode != null) {
				pw.println(gcode);
				gcode = reader.readLine();
			}
			socket.shutdownOutput();

			reader.close();
			pw.close();
			socket.close();
			print("client shut down!\n");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
