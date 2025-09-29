# 📥 과제 제출 가이드

스터디 과제를 제출하는 방법을 정리한 문서입니다.  
아래 절차에 따라 본인 과제를 제출해주세요.  

---

## 1️⃣ 저장소 Fork
- GitHub에서 스터디 원본 저장소를 **Fork** 합니다.  
- Fork한 저장소는 본인의 계정에 복사됩니다.
<br/>
<img width="1222" height="819" alt="image" src="https://github.com/user-attachments/assets/c9bcc4b3-97c5-4a0a-b847-a610bbbff45c" />
<img width="1255" height="640" alt="image" src="https://github.com/user-attachments/assets/06e59647-5f02-4726-9c52-69055d52633d" />



---

## 2️⃣ 저장소 Clone
- Fork한 본인 저장소를 로컬로 Clone 합니다.
- git이 없을시 git 검색후 설치해주세요.
- git bash에 밑에 명령어를 입력해주세요.
```bash
git clone https://github.com/<본인-아이디>/<저장소이름>.git
```
VSCode에서 clone한 폴더를 엽니다.  
<br/>
<img width="1902" height="755" alt="clone" src="https://github.com/user-attachments/assets/0f2cf5cf-a65e-4ee5-97a3-99a4eaf384d5" />


---

## 3️⃣ 폴더 생성 규칙
- `week<주차>/본인이름영문/` 구조로 폴더를 생성합니다.  
  - 예시: `week01/yechan/`  

- 각 폴더 안은 다음과 같이 구성합니다:  
  - `common/` → 공통 문제 풀이  
  - `personal/` → 개인 문제 풀이  

---

## 4️⃣ 과제 업로드
- 문제 풀이 코드를 해당 폴더에 넣고, 터미널에서 아래 명령어 실행:
```bash
git add .
git commit -m "n주차 제출 - 이름"
git push origin main
```

## 5️⃣ Pull Request (PR) 생성
1. GitHub에서 원본 저장소로 **Pull Request** 생성  
2. 리뷰어(Reviewer), 어싸이니(Assignee) 지정  
3. PR 템플릿을 사용해도 되고, 자유롭게 작성해도 됩니다.
<img width="975" height="693" alt="image" src="https://github.com/user-attachments/assets/a7b87536-dc45-4432-8264-4fca8781ed27" />
<img width="979" height="729" alt="image" src="https://github.com/user-attachments/assets/882d85cf-a2c0-490b-b9bb-8861c7b5551b" />
<img width="980" height="862" alt="image" src="https://github.com/user-attachments/assets/61cd7ad9-2c34-42c8-bb9d-7f9e5d027d3c" />


---

## 6️⃣ 동기화 (Sync 맞추기)
- 새로운 과제 시작 전에 반드시 **Sync Fork**를 눌러 원본 저장소와 동기화해주세요.  
<img width="963" height="854" alt="image" src="https://github.com/user-attachments/assets/53b5b72f-0c50-4172-9c0f-34572532cb2e" />


---

## ✅ 요약 플로우
1. 저장소 Fork → Clone  
2. `week<주차>/<본인영문이름>/common` & `personal` 생성  
3. 코드 작성 후 add → commit → push  
4. PR 생성 (리뷰어/어싸이니 지정)  
5. 다음 과제 전 **Sync Fork**

