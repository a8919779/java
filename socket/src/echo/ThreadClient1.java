package echo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadClient1 extends Thread {

	private Socket sock;
	public ThreadClient1(Socket sock) {
		this.sock = sock;
	}
	
	public void run() {
		try {
			sock = new Socket("127.0.0.1", 10001);
			BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
			OutputStream out = sock.getOutputStream();
			InputStream in = sock.getInputStream();
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = null;
			
			while ((line = key.readLine()) != null) {
				if (line.equals("quit")) {
					break;
				}
				
				pw.println(line);
				pw.flush();
				String echo = br.readLine();
				System.out.println("서버로부터 전달받은 문자열 : "+echo);
			}
			
			if (pw != null) {
				pw.close();
			}
			if (br != null) {
				br.close();
			}
			if (sock != null) {
				sock.close();
			}
		} catch(Exception e) {
			System.out.println(e);
		}

	}
}
