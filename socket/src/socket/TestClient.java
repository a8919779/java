package socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TestClient tc = new TestClient();
		
		try {
			tc.exe();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void exe() throws UnknownHostException, IOException {
		Socket s= null;
		BufferedReader br = null;
		PrintWriter pw = null;
		BufferedReader br2 = null;
		
		try {
			s = new Socket("127.0.0.1", 7777);
			System.out.println("서버 연결");
			br = new BufferedReader(new InputStreamReader(System.in));
			pw = new PrintWriter(s.getOutputStream(), true);
			br2 = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String str;
			
			while (true) {
				System.out.println("서버에 보낼 메세지");
				str = br.readLine();
				
				if (str.equalsIgnoreCase("exit")) {
					break;
					
				}
				pw.println(str);
			}
		} finally {
			if (br2 != null) {
				br2.close();
			}
			
			if (pw!= null) {
				pw.close();
			}
			
			if (s != null) {
				s.close();
			}
		}
	}

}
