# 📥 과제 제출 가이드

스터디 과제를 제출하는 방법을 정리한 문서입니다.  
아래 절차에 따라 본인 과제를 제출해주세요.  

---

## 1️⃣ 저장소 Fork
- GitHub에서 스터디 원본 저장소를 **Fork** 합니다.  
- Fork한 저장소는 본인의 계정에 복사됩니다.  

---

## 2️⃣ 저장소 Clone
- Fork한 본인 저장소를 로컬로 Clone 합니다.
```bash
git clone https://github.com/<본인-아이디>/<저장소이름>.git
```
VSCode에서 clone한 폴더를 엽니다.  

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

---

## 6️⃣ 동기화 (Sync 맞추기)
- 새로운 과제 시작 전에 반드시 **Sync Fork**를 눌러 원본 저장소와 동기화해주세요.  

---

## ✅ 요약 플로우
1. 저장소 Fork → Clone  
2. `week<주차>/<본인영문이름>/common` & `personal` 생성  
3. 코드 작성 후 add → commit → push  
4. PR 생성 (리뷰어/어싸이니 지정)  
5. 다음 과제 전 **Sync Fork**

