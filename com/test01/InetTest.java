package com.test01;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetTest {
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
	 */
	
	public static void main(String[] args) throws UnknownHostException {
		
		InetAddress addr = InetAddress.getLocalHost();
		//지역호스트의 호스트명과 IP 주소를 반환하는 메소드
		//InetAddress 클래스의 레퍼런스 변수 addr을 만들어 getLocalHost 메소드를 이용해
		//내 PC의 이름과 IP주소를 레퍼런스 변수에 받아온다.
		
		System.out.println(addr);
		//내 PC의 IP 주소 출력
		
		System.out.println("Local Host : " + addr.getHostAddress());
		//getHostAddress 메소드를 이용해 IP주소만 가져와 출력
		
		InetAddress [] naver = InetAddress.getAllByName("www.naver.com");
		//도메인 명에 지정된 모든 호스트명과 호스트의 IP주소를 배열에 담아 반환하는 메소드
		
		for(int i = 0; i < naver.length; i++) {
			System.out.println(naver[i].getHostName() + " : " + naver[i].getHostAddress());
		}
		//InetAddress 배열에 담긴 호스트명과 IP주소를 갖고, getHostName과 getHostAddress 메소드를 통해
		//호스트명과 호스트의 IP주소를 출력한다.
		
		for(int i = 0; i < naver.length; i++) {
			System.out.println(naver[i].getHostName() + " : " + naver[i]);
		}
		
	}

}
