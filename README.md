# Android JAVA Example


### android-fileupload-sample-master
- intent를 통해 갤러리의 이미지를 가지고 온 후 멀티파트를 통해 이미지 업로드를 하는 예제


### AndroidTensorFlowMachinLearningExample-Master
- Camera2를 직접 구현하는 camerakit을 사용하여 찍은 이미지를 tensorflow-lite를 통해 머신러닝 후 이미지 인식

### AndroidId
- 실행한 디바이스의 AndroidId와 설치된 모든 앱의 이름, 패키지명, 클래스명을 보여준다
  (Android ID는 디버그모드와 릴리즈모드가 다르니 주의해야함)  

### CodeView-master
- 모바일에서 개발툴에서 작성하는 코드 작성이 가능한 뷰, Python과 Java를 지원한다
  (Run은 불가능)

### CropImage
- Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); 사진 찍기
  Intent intent = new Intent(Intent.ACTION_PICK); 앨범에서 사진 선택
  Intent intent = new Intent("com.android.camera.action.CROP"); 앨범에 있는 사진 크롭 

### excelPanel-master
- excelpanel 라이브러리를 사용한 숙박업소용 캘린더

### floation-layout-android-master
- floatinglayout 라이브러리를 통해 다른 앱 위에 창모드 형식의 뷰를 구현 (ACTION_MANAGE_OVERLAY_PERMISSION을 허용 해야함)
								
### Google STT(speech to text)
- android.speech의 SpeechRecognizer 클래스를 통해 구현, 녹음 기능에 대한 퍼미션을 허용 해야함

### Google TTS(Text to speech)
- android.speech.tts의 TextToSpeech 클래스를 통해 구현, 버튼을 통해 목소리 톤 up, down과 말하기 속도 up, down 을 할 수 있음

### handwriting-detector-app-master
- tensorflow-lite를 통해 네모 안에 숫자를 쓰면 인식하여 textview에 뿌려 주는 앱

### location-samples-master
- location 관련 앱들

### Mnist-master
-Tensorflow-lite를 통해 canvasview에 적힌 숫자를 학습 머신러닝 후 정확도를 보여 주는 앱

### ModalBottomSheetDialog-master
-커스텀 바텀뷰(그리드, 라운드, 스크롤 해더포함 등)

### MPAndroidChat-master
-MPChart 라이브러리를 사용한 차트 앱들

### ImageCaptureActivity
-화면 위에 canvas를 통해 그림을 그리고 캡쳐하기 버튼 클릭 시 화면이 저장된 캐시를 비트맵으로 변환하여 저장하는 앱

### QRpos
-zxing라이브러리의 IntentItegrator를 통해 QR스캔이 가능한 내부 카메라를 실행, QR스캔 성공 후 다시 QR스캐너 모드로 동작함

### NetflixClone-development
-Netflix UI를 Clone한 예제

### ServiceExample-master (https://stickode.tistory.com/272)
- 1.boot_completed(BroadcastReceiver)휴대폰이 켜졌는지를 확인
  2. SDK_INT >= Oreo 이라면 startForegroundService 를 통해 RestartService 실행
     SDK_INT < Oreo 라면 startService 를 통해 RealService 실행
  3. AlarmRecever(BroadcastReceiver) 를 통해서 RealSerivce 가 죽었을때 Alarm 으로 실행

### ShakeTest(https://stickode.tistory.com/m/108)
- 가속도계 센서를 사용하여 중력, 중력가속도를 기준으로 진동, 움직임을 측정

### Sorket
- Android service에서 Thread를 통해 ServerSocket을 열고 대기 후 MainActivity에서 Thread로 클라이언트 socket을 통해 서버에 데이터를 보낸 후 다시 받아서 textview에 setText

### SQLite
- SQLite Database를 활용하여 데이터를 listview에 뿌려주는 예제

### SSNCreate
- 주민번호 검증 로직을 적용한 주민번호 생성기

### tensorflow-hangul-recognition-master
- tensorflow 라이브러리를 통해 draw된 이미지를 인식 후 높은 정확도의 글자를 edittext에 입력 후 나머지 글자들은 button에 입력해 눌러서 입력 가능하도록 구현되어 있는 예제

### TextDetectioUsingMLKit-master
- firebase ML Kit 으로 카메라를 통해 OCR하는 예제

### TimeLineDatePicker
- timelineview 라이브러리를 통한 상단 고정 달력 예제

### VmAndroid
- MVVM패턴의 ViewModel과 jetpack의 lifedata로 구현한 숫자 카운터 앱

### WebView 
- webview 구현 후 TouchListener로 터치 값 확인하는 예제

### zxing-android-embedded-master 
- zxing 라이브러리 구현 예제
