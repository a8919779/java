package echo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class EchoThread extends Thread {
	private Socket sock;
	public EchoThread(Socket sock) {
		this.sock = sock;
	}
	
	public void run() {
		BufferedReader fbr = null;
		BufferedReader ebr = null;
		try {
			InetAddress inetaddr = sock.getInetAddress();
			System.out.println("["+getTime()+"] ########");
			System.out.println(inetaddr.getHostAddress() + " 로 부터 접속하였습니다");

			BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));

			String line = null;
			String webroot = readJson("path", "info1");
			String srcfile = "";
			String filename = "";
			String hostname = "";

			HashMap<String, String> map = new HashMap<String, String>();

			while (!"".equals(line = br.readLine())) {
			String [] wo = line.split(":");
			

				if (wo.length < 2) {
					System.out.println("wo[0] : "+wo[0]);
					int start = line.indexOf(" ") + 2;
					int end = line.lastIndexOf("HTTP") -1;
					filename = line.substring(start, end);
		
					// 상대경로 접근 시 오류페이지
					if (filename.contains("./")) {
						filename = readJson("err", "info2");
					}
		
					if ("".equals(filename)) {
						filename = readJson("file", "info1");
					}
	
				} else {
					System.out.println("wo[0] : "+wo[0] + " / wo[1] : " + wo[1] == null ? "" : wo[1]);
					map.put(wo[0], wo[1]);
					if ("Host".equals(wo[0])) {
						hostname = wo[1].replaceAll(" ", "");
					}
				}
			}
			String tmpurl = readJson("url", "info2");
			if (tmpurl.equals(hostname)) {
				webroot = readJson("path", "info2");
			}
	
			srcfile = webroot + filename;
			System.out.println("사용자가 " + filename + "을 요청했습니다.");

			// 사용자 접근 경로 확인 후 해당 경로에 파일이 존재하지 않을경우 모든 디렉토리 검색
			if (!getFile(fbr, pw, srcfile)) {
				//URL에 경로까지 입력했을경우 파일명만 구해오기
				if (filename.lastIndexOf("/") != -1) {
					filename = filename.substring(filename.lastIndexOf("/")+1);
				}
	
				File dfile = new File(webroot);
	
				if (dfile.isDirectory()) {
					//찾고자 하는 파일 디렉토리 검색
					srcfile = getFilename(dfile, filename);
				} else {
					System.out.println("is not dir");
				}
	
				// 파일 경로를 찾지못했을경우 404페이지 이동
				if (!getFile(fbr, pw, srcfile)) {
					ebr = new BufferedReader(new FileReader(webroot + readJson("err", "info1")));
		
					String eline = null;
					while ((eline = ebr.readLine()) != null) {
						pw.println(eline);
						pw.flush();
					}
				}
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
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	static String getTime() {
		String name = Thread.currentThread().getName();
		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
		return f.format(new Date()) + name;
	}

	public String getFilename(File f, String filename) {
		File[] fileList = f.listFiles();
		String filepath = "";
		String temppath = "";
	
		for (int i=0;i<fileList.length;i++) {
			if (fileList[i].isDirectory()) {
				temppath = getFilename(fileList[i], filename);
				if (!("".equals(temppath))) {
					filepath = temppath;
				} else {
					if (filename.equals(fileList[i].getName())) {
						filepath = fileList[i].getAbsolutePath();
					}
				}
			}
		}
		
		return filepath;
	}

	public boolean getFile(BufferedReader fbr, PrintWriter pw , String srcfile) throws IOException {
		try {
			fbr = new BufferedReader(new FileReader(srcfile));
		
			String fline = null;
			while ((fline = fbr.readLine()) != null) {
			pw.println(fline);
			pw.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static String readJson(String info, String num) throws IOException {
		String rtnVal = "";
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jo = (JSONObject)jsonParser.parse(new FileReader("/Users/boramlee/Documents/workspace/Study-boram/src/conf/conf.json"));
		
			JSONArray jsonArray = (JSONArray)jo.get(info);
		
			for (int i=0;i<jsonArray.size();i++) {
				JSONObject ifObject = (JSONObject) jsonArray.get(i);
				rtnVal = (String)ifObject.get(num);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return rtnVal;
	}

}
