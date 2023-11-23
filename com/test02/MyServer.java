package com.test02;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.io.IOException;
import java.net.DatagramPacket;

public class MyServer {
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
	 * [UDP]
	 * 전송 속도가 빠르지만 신뢰성 없는 데이터를 전송하는 비연결 지향 프로토콜
	 * 별도의 연결 요청이나 전달하거나 받은 데이터를 확인하는 과정이 없다.
	 */
	
	//UDP 통신 (서버)
	//비연결 지향이기 때문에 연결 요청을 받거나할 서버소켓이 필요없다.
	//두개의 DatagramSocket 간에 DatagramPacket으로 변환된 데이터를 주고 받는다.
	public static void main(String[] args) throws SocketException, UnknownHostException {
		//소켓 생성 (DatagramSocket)
		DatagramSocket ds = new DatagramSocket();
		//예외처리 해야하나 throws로 jvm에 던졌다.
		System.out.println("서버 입니다.");
		
		//전송할 데이터
		//byte 배열로 담는다.
		byte [] buff = "연습입니다.".getBytes();
		
		//DatagramPacket으로 변환 (데이터, 데이터 길이, 주소, 포트)
		DatagramPacket sendP = new DatagramPacket(buff, buff.length, InetAddress.getByName("localhost"), 9999);
		//(데이터, 데이터 길이, 주소, 포트)
		//getByName 예외 처리해야하나 throws로 jvm에 던졌다.
		
		//전송
		try {
			ds.send(sendP);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//종료
		ds.close();
		ds.disconnect();
	}

}
