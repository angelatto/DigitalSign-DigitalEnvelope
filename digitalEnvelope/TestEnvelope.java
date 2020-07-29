package digitalEnvelope;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.Formatter;
import java.util.Scanner;

import javax.crypto.SecretKey;

import common.*;
import digitalSign.DigitSign;
import digitalSign.TestSign;

// 전자봉투 생성을 테스트하는 클래스
public class TestEnvelope {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("******************************");
		System.out.println("Make Digital Envelope");
		System.out.println("******************************");
		
		DigitSign digitSign = new DigitSign();

		//파일 생성
		System.out.print("Enter Your File Name For Sign : ");
		String fileName = scan.next();
		
		FileOutputStream output = null;
		BufferedReader br = null;
		try {
			output = new FileOutputStream(fileName);
			System.out.print("Enter Your Data For Sign : ");
			br = new BufferedReader(new InputStreamReader(System.in));
			output.write(br.readLine().getBytes());
		
			System.out.print("Enter Your Signature File Name : ");
			String sigFile = scan.next();
			
			System.out.print("Enter Your Public Key File Name For Sign : ");
			String pubFile = scan.next();
			
			System.out.print("Enter Your Private Key File Name For Sign : ");
			String priFile = scan.next();
			
			System.out.print("Enter Secret Key File Name For Sign : ");
			String secretFile = scan.next();
			
			System.out.print("Enter Receiver's Public Key File Name : ");
			String recPubFile = scan.next();
			
			System.out.print("Enter File Name For Send : ");
			String sendFile = scan.next();
		
			// 전자서명 생성
			FileIO fileTool = new FileIO();
			byte[] sign = digitSign.sign(fileName, sigFile, priFile);
			String makeFile = fileTool.makeFile(sign, sigFile);
			
			if(makeFile == null) {
				throw new MakeFileException();
			}
			
			// 평문, 전자서명, 인증서를 객체에 담아 직렬화하기 
			PlainSet plainSet = new PlainSet(fileName, sign, pubFile);
		
			SerialSet serialSet = new SerialSet();
			byte[] dataSet = serialSet.serialization(plainSet);
			
			//AES 비밀키 생성해서 encryptSet을 암호화한다.
			MySecretKey mySecretKey = new MySecretKey();
			
			SecretKey secretKey = (SecretKey)mySecretKey.restoreKey(secretFile);
			
			// 비밀키를 수신자 B의 공개키로 암호화 ---> 이것이 전자봉투이다.
			PublicKey publicKey = (PublicKey)mySecretKey.restoreKey(recPubFile);
			byte[] envelope = mySecretKey.encrypt(publicKey, secretKey.getEncoded());
			
			//그 세가지 데이터를 직렬화한 byte[]를 비밀키로 암호화한다.
			byte[] encryptDataSet = mySecretKey.encrypt(secretKey, dataSet);
			
			// 세가지 암호화된 결과물과 전자봉투를 묶어서 최종적으로 직렬화해서 파일에 저장
			VerifySet verifySet = new VerifySet(encryptDataSet, envelope); //dataSet
			
			byte[] finalSet = serialSet.serialization(verifySet);
			String rslt = fileTool.makeFile(finalSet, sendFile);
			
			System.out.println("******************************");
			System.out.println("Send Success : " + rslt);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			System.out.println("정상적인 파일 이름이 아닙니다.");
		} catch (NoSuchAlgorithmException | ClassNotFoundException | SignatureException e) {
			e.printStackTrace();
		} catch (MakeFileException e) {
			System.out.println(e.getMessage() + "는 없는 파일 입니다.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				output.close();
				br.close();
				scan.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Finish");
		System.out.println("******************************");
	}

}
