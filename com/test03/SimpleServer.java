package com.test03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
	/* [서버]
	 * 서비스를 제공하는 프로그램
	 * 요청 처리 후 응답하는 역할
	 * 
	 * [클라이언트]
	 * 서비스를 받는 프로그램
	 * 요청하고 응답을 받는 역할
	 * 
	 * [IP 주소]
	 * 네트워크 상에서 각 컴퓨터들을 식별하는 번호
	 * 랜카드마다 할당
	 * 중복 없다.
	 * 
	 * [포트] (Port)
	 * 동일 클라이언트 내에서 프로그램을 식별하는 번호
	 * 동일 클라이언트 내에서 포트는 중복 없다.
	 * 서버 연결 요청 시 IP 주소와 포트 번호를 알아야한다.
	 * 
	 * [TCP]
	 * 전송 속도는 느리지만 정확하고 안정적으로 전달하는 연결 지향적 프로토콜
	 * 별도의 연결요청 또는 전달하거나 받은 데이터를 확인하는 과정이 있다.
	 */
	
	//TCP 통신 (서버)
	//클라이언트와 서버 간에 1:1 소켓 통신
	//서버용 프로그램, 클라이언트용 프로그램을 각각 구현
	//서버가 먼저 실행된 다음, 클라이언트에서 요청이 올 때까지 기다려야한다. (연결 요청)
	//ServerSocket과 Socket 클래스를 사용한다.
	public static void main(String[] args) {
		//변수 선언
		ServerSocket ss = null;
		//ServerSocket은 클라이언트로부터 들어오는 연결요청을 받는 소켓
		Socket sk = null;
		//Socket은 연결 요청 수락 후 클라이언트와 연결을 하기위한 소켓
		
		//출력 스트림 변수 선언
		PrintWriter out = null;
		//입력 스트림 변수 선언
		BufferedReader in = null;
		
		try {
			//ServerSocket 객체 생성
			//포트 번호는 9999
			//test02와 동일한 포트를 사용하니까 미리 종료해놓기
			ss = new ServerSocket(9999);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true) {
			System.out.println("Client를 기다립니다.");
			
			try {
				sk = ss.accept();
				//accept 메소드로 ServerSocket으로 온 요청을 수락한다.
				//연결 요청 수락하면 Socket에 연결을 진행한다.
				System.out.println("Client가 접속했습니다.");
				
				//1. 클라이언트에게 받은 내용을 라인단위로 읽어들인다.
				//BufferedReader 스트림 사용
				
				in = new BufferedReader(new InputStreamReader(sk.getInputStream()));
				//스트림 객체 생성
				//기반 스트림인 getInputStream 으로 통로를 만들고
				//보조 스트림인 InputStreamReader를 이용해 바이트를 문자 단위로 변환하고
				//보조 스트림인 BufferedReader로 버퍼에 있는 변환내용을 읽어들인다.
				
				//2. 읽어들인 내용을 다시 보내준다.
				//스트림 객체 생성
				out = new PrintWriter(sk.getOutputStream(), true);
				//기반 스트림인 getOutputStream을 이용해 통로를 만들고
				//보조 스트림인 PrintWriter로 내용을 내보낸다.
				
				String inputLine = null;
				while((inputLine = in.readLine()) != null) {
					System.out.println("Client가 보낸 메세지 : " + inputLine);
					out.println("[서버가 다시 보낸 메세지] " + inputLine);
				}
				//readLine은 더 보낼 내용이 없으면 null을 보낸다.
				//null 값을 받으면 조건식이 false를 결과값으로 가지므로 반복문을 종료한다.
				
				//종료
				out.close();
				in.close();
				ss.close();
				sk.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//서버 먼저 실행하고 그다음에 클라이언트 실행해서 입력해야한다.
		
		
	}

}
