package com.test03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient {
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
	
	//TCP 통신 (클라이언트)
	//클라이언트와 서버 간에 1:1 소켓 통신
	//서버용 프로그램, 클라이언트용 프로그램을 각각 구현
	//서버가 먼저 실행된 다음, 클라이언트에서 요청이 올 때까지 기다려야한다. (연결 요청)
	//ServerSocket과 Socket 클래스를 사용한다.
	public static void main(String[] args) {
		//변수 선언
		Socket clientSocket = null;
		Scanner sc = new Scanner(System.in);
		
		//스트림 변수 선언
		PrintWriter out = null;
		BufferedReader in = null;
		
		System.out.println("서버에 접속합니다....");
		
		try {
			//소켓 객체 생성
			//서버로 연결 요청
			//(호스트명, 포트 번호)
			clientSocket = new Socket("localhost",9999);
			
			//스트림 객체 생성
			out = new PrintWriter(clientSocket.getOutputStream(), true);//true는 플러시 기능
			//기반 스트림인 getOutputStream을 이용해 통로를 만들고
			//보조 스트림인 PrintWriter로 내용을 내보낸다.
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			//기반 스트림인 getInputStream 으로 통로를 만들고
			//보조 스트림인 InputStreamReader를 이용해 바이트를 문자 단위로 변환하고
			//보조 스트림인 BufferedReader로 버퍼에 있는 변환내용을 읽어들인다.
			
			
			String inputLine;
			while ((inputLine = sc.nextLine()) != null) {
				out.println(inputLine);
				//클라이언트에서 보내는 메세지
				System.out.println("서버로부터 다시 받은 메시지 : " + in.readLine());
			}
			
			//종료
			sc.close();
			in.close();
			out.close();
			clientSocket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
