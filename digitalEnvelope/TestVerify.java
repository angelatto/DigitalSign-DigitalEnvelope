package digitalEnvelope;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import common.FileIO;

public class TestVerify {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		System.out.println("******************************");
		System.out.println("Verify Digital Envelope");
		System.out.println("******************************");
		
		System.out.print("Enter Received File Name For Verify : ");
		String fileName = scan.next();
		
		System.out.print("Enter Your Private Key File Name For Verify : ");
		String priFile = scan.next();
	
		try {
			MySecretKey mySecretKey = new MySecretKey();

			PrivateKey privateKey = (PrivateKey)mySecretKey.restoreKey(priFile); //영희의 사설키 
			
			SerialSet serialSet = new SerialSet();
			FileIO fileTool = new FileIO();
			byte[] receiveSet = fileTool.readFile(fileName);
			
			VerifySet verifySet = (VerifySet) serialSet.deserialization(receiveSet);		
	
			// 수신자가 받은 전자봉투
			byte[] envelope = verifySet.getEncryptEnvelope();
	
			// 수신자의 개인키로 전자봉투를 복호화하여 비밀키 복구 -> 바이트 배열 !!!!!!!
			byte[] receiveSKey = mySecretKey.decryptEnvelope(privateKey, envelope);
			SecretKey secretKey = new SecretKeySpec(receiveSKey, "AES");
			
			//수신자가 받은 암호문 세트
			byte[] receiveDataSet = verifySet.getEncryptSet();
			
			PlainSet receivePlainSet = mySecretKey.decryptSet(secretKey, receiveDataSet);
       
			// 송신자의 공개키로 전자서명 검증
			DigitEnv digitSign = new DigitEnv();
			boolean rslt = digitSign.verify(receivePlainSet.getDataFileName(), receivePlainSet.getSignature(), receivePlainSet.getPubFileName());
			System.out.println("******************************");
			if(rslt) {
				System.out.println("Verify Success !!");
			} else {
				System.out.println("Verify Failed !!");
			}
			
		} catch (InvalidKeyException | SignatureException | ClassNotFoundException | NoSuchAlgorithmException
				| NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | IOException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} finally {
			scan.close();
		}

		System.out.println("Finish");
		System.out.println("******************************");
	}

}