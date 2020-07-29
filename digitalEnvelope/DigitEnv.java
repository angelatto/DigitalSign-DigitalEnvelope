package digitalEnvelope;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import digitalSign.DigitSign;

// 전자서명 생성, 검증
public class DigitEnv extends DigitSign{

	private static MySecretKey mySecretKey = new MySecretKey();

	//keyFileName privateKey로 바꿔야함.
	byte[] sign(String dataFileName, String keyFileName) 
			throws NoSuchAlgorithmException, IOException, ClassNotFoundException, SignatureException, InvalidKeyException {
		
		// Create an instance
		Signature signature = Signature.getInstance(signAlgorithm);
	
		// 공개 키 파일을 읽어 key 가져오기
		PrivateKey privateKey = (PrivateKey)mySecretKey.restoreKey(keyFileName);
		// Initialize the signer with private or public key
		signature.initSign(privateKey);
		
		byte[] datafile = fileTool.readFile(dataFileName);
		if (datafile == null) {
			System.out.println("DataFile not exist");
			return null;
		}
		// add data for verification
		signature.update(datafile);
		
		byte[] signatureData = signature.sign();
		return signatureData;
	}
	
	public boolean verify(String dataFileName, byte[] sigData, String keyFileName) 
			throws SignatureException, ClassNotFoundException, IOException, NoSuchAlgorithmException, 
					NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {	
		
		// Create an instance
		Signature signature = Signature.getInstance(signAlgorithm);
		
		// 공개 키 파일을 읽어 key 가져오기
		PublicKey publicKey = (PublicKey)mySecretKey.restoreKey(keyFileName);

		// Initialize the signer with private or public key
		signature.initVerify(publicKey);
		
		// Add data for signing
		byte[] data = fileTool.readFile(dataFileName);
		signature.update(data);
		
		// Verify the signature
		return signature.verify(sigData);
	}

}
