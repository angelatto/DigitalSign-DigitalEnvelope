package digitalEnvelope;

import java.io.Serializable;

//수신자가 전달받은 객체 (암호문, 전자봉투)
public class VerifySet implements Serializable{
	
	// final Variable
	private static final long serialVersionUID = 1L;
	
	// Variable
	byte[] encryptSet; //plainSet을 암호화한것
	byte[] encryptEnvelope; // 전자봉투

	// Constructor
	public VerifySet() {
		super();
	}
	
	public VerifySet(byte[] encryptSet, byte[] encryptEnvelope) {
		this.encryptSet = encryptSet;
		this.encryptEnvelope = encryptEnvelope;
	}
	
	// Setter & Getter
	public byte[] getEncryptSet() {
		return encryptSet;
	}
	public void setEncryptSet(byte[] encryptSet) {
		this.encryptSet = encryptSet;
	}
	public byte[] getEncryptEnvelope() {
		return encryptEnvelope;
	}
	public void setEncryptEnvelope(byte[] encryptEnvelope) {
		this.encryptEnvelope = encryptEnvelope;
	}
}
