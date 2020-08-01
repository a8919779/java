package echo;

import java.net.Socket;

public class EchoClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Socket sock = new Socket("127.0.0.1", 10001);
			System.out.println("접속을 시도합니다..");
			
			while (true) {
				ThreadClient1 tc1 = new ThreadClient1(sock);
				tc1.start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
}
