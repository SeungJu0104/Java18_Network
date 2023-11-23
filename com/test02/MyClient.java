package com.test02;

import java.net.DatagramSocket;
import java.io.IOException;
import java.net.DatagramPacket;

public class MyClient {
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
	
	//UDP 통신 (클라이언트)
	//비연결 지향이기 때문에 연결 요청을 받거나할 서버소켓이 필요없다.
	//두개의 DatagramSocket 간에 DatagramPacket으로 변환된 데이터를 주고 받는다.
	public static void main(String[] args) throws IOException {
		//소켓 생성 (DatagramSocket)
		DatagramSocket ds = new DatagramSocket(9999);
		//예외처리 해야하나 throws로 jvm에 던졌다.
		//9999는 포트 번호로 UDP 통신 (서버) (즉, MyServer 클래스)에서 데이터 전송에서 사용했던 포트 번호이다.
		System.out.println("클라이언트");
		
		//전송받은 데이터 저장 공간 생성
		//데이터 저장할 Byte 배열 생성.
		byte [] buff = new byte [1024];
		
		//받은 데이터를 DatagramPacket으로 변환
		//(데이터, 데이터 길이)
		DatagramPacket receiveP = new DatagramPacket(buff, buff.length);
		//(데이터, 데이터 길이)
		
		//서버에서 보낸 패킷 저장
		ds.receive(receiveP);
		System.out.println(new String(receiveP.getData()));
		//전송받은 DatagramPacket을 getData를 이용해 byte로 가져오고,
		//이걸 String 객체를 생성해서 담아 출력한다.
		//안담으면 주소 값? 해시 값?만 나오기 때문에 String 객체에 담는다.
		
		//종료
		ds.close();
		ds.disconnect();
		
		//MyClient 클래스 실행한 뒤, MyServer 클래스를 실행하면 전송한 데이터("클라이언트")가 출력된다.
		//다른 클래스에 별도의 스레드를 만들어 동시에 동일한 포트 번호로 데이터를 전송하면,
		//동일 클라이언트 내에서 중복된 포트 번호를 사용할 수 없기 때문에 포트 쓸 수 없다는 오류가 나온다.
	}

}
