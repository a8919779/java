package echo;

import java.net.ServerSocket;
import java.net.Socket;

public class EchoThreadServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			ServerSocket server = new ServerSocket(Integer.parseInt(EchoThread.readJson("host","info2")));
			System.out.println("접속을 기다리는 중..");
			
			while (true) {
				Socket sock = server.accept();
				EchoThread echothread = new EchoThread(sock);
				echothread.start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
