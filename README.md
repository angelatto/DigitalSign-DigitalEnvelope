# DigitalSign-DigitalEnvelope
전자서명과 전자봉투를 생성하고 검증하는 시스템을 디자인 및 구현하기

## 0) common.package - 전자서명과 전자봉투에서 공통적으로 사용하는 파일 

- FileIO : 파일 생성, 파일 읽기
- FileNameException : 비정상적인 파일 이름에 대해 에러 처리
- GenerateRSAKey :  key file name을 입력받아  RSA키를 생성하고 저장 (Main메소드)
- MakeFileException : 파일 생성 오류 처리
- MyKeyPair : RSA키를 생성하고 관리하는 객체 클래스
- SerialKey : KEY를 외부파일에 직렬화하고, 역직렬화

## 1) digitalSign.package - 전자서명

- DigitSign : 서명하고, 검증하는 메소드가 있는 클래스
- TestSign :  전자서명을 진행하는 메인 메소드가 있는 클래스 (Main메소드)
- TestVerify : 인증서를 검증하는 메소드가 있는 클래스 (Main메소드)

## 2) digitalEnvelope.package - 전자봉투

- DigitEnv : DigitSign 을 상속받아 전자서명을 생성하고, 오버로딩하였다.
- GenerateAESKey : AES 키를 생성하는 메인 클래스  (Main메소드)
- MySecretKey :  SerialKey 클래스를 상속받음. 비밀키를 생성하고 저장하는 객체
                 비밀키를 이욯한 암호화, 복호화 메소드가 내장된 클래스

- PlainSet:  평문파일, 전자서명, 인증서를 하나의 객체에 저장
- SerialSet:  PlainSet을 직렬화하고 역직렬화
- TestEnelope : 송신자입장에서 전자봉투를 생성하고 이용하기 위한 파일이름들을 입력받고, 실행하는 메인 메소드(Main메소드)
- TestVerify ; 수신자입장에서 전송받은 암호문과 전자봉투를 검증하는 클래스 (Main메소드)
- VerifySet :  plainSet을 암호화한것과 전자봉투를 하나의 객체에 저장

## 3) 클래스 설계 강조하고 싶은 부분 및 자바 시큐어리티

- 전자봉투에서 평문, 전자서명, 인증서를 한번에 담은 객체를 생성하여 직렬화한 후에 비밀키로 암호화하였다. 
  여기서 생성된 암호문과 전자봉투를 담은 객체를 생성하여 직렬화하여 영희에게 전송하였다.
- 평문 데이터 파일을 생성할때 파일 이름을 입력받고, 또한 데이터를 BufferedReader로 입력받았다.
- 전자서명에서는 서명과 검증, 전자봉투에서는 송신자와 수신자입장을 각각 고려하여 클래스를 생성하였고, 독립적으로 main함수를 실행하였다.
- 접근 제어자를 최대한 protected와 private을 사용하여 객체 사용범위를 최소화하였다.
- void반환형 메소드는 메소드의 정상적인 실행 결과 여부를 알기 어려움으로 최대한 return값을 주어 
  메소드의 실행 결과를 한눈에 알아볼 수 있게 설계하였다. 
- 중복되는 기능은 common패키지로 분류하여 각각 전자서명과 전자봉투에서 호출하도록 하고,
  전자서명의 DIgitSIgn을 부모클래스로 사용하고 전자봉투의 DigitSign에서 상속받아서 나머지 메소드를 정의하였다. 
- 암호화 복호화 메소드는 전자서명과 전자봉투에서 하는 일은 같지만, 사용하는 파라미터 개수와 타입이 다름으로 오버로딩하여 자바의 다형성을 이용하였다.
