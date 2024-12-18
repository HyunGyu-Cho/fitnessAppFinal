# Fitness App

## 📋 프로젝트 설명
사용자는 회원가입 및 로그인 후, 자신만의 운동 루틴을 생성하고, 루틴에 운동을 추가 및 관리할 수 있습니다. 또한 각 운동에 대한 상세정보를 확인할 수 있습니다.

## 🛠️ 개발 환경
- **플랫폼**: Android (Native)
- **언어**: Java
- **IDE**: Android Studio

## 주요 기능

- **회원가입 및 로그인**: 사용자 정보(이름, 나이, 키, 몸무게, ID, PW)를 이용해 회원가입 가능.
- **루틴 생성 및 관리**: 
  - 새로운 루틴 생성
  - 생성한 루틴의 이름 수정
  - 루틴 삭제
- **운동 추가 및 관리**: 
  - 운동 목록에서 원하는 운동을 루틴에 추가
  - 운동 목록의 항목을 길게 눌러 루틴에서 해당 운동을 제거
  - 운동명을 클릭하여 운동 상세정보(에너지소비량, 유형) 확인

## 📂 프로젝트 구조

- `app/src/main/java/kr/co/softcampus/firnessapp/`
  - `LoginActivity.java`: 로그인 화면 액티비티
  - `RegisterActivity.java`: 회원가입 화면 액티비티
  - `MainActivity.java`: 메인 화면, 현재 사용자에 대한 인사말 및 루틴 선택 UI
  - `RoutineListActivity.java`: 사용자의 루틴 목록 관리 화면
  - `CreateRoutineActivity.java`: 새로운 루틴을 생성하는 화면
  - `RoutineDetailActivity.java`: 특정 루틴에 대한 상세 화면(운동 목록 관리)
  - `SelectExerciseActivity.java`: 운동 목록에서 루틴에 운동을 추가하는 화면
  - `ExerciseDetailActivity.java`: 특정 운동에 대한 상세정보를 보여주는 화면
  - `DBHelper.java`: SQLite DB helper class, 사용자/루틴/운동 데이터 관리 로직 포함
  - `User.java`, `Routine.java`, `Exercise.java`, `RoutineExercise.java`: data model class
  
- `app/src/main/res/layout/`
  - `activity_login.xml`, `activity_register.xml`, `activity_main.xml`, `activity_routine_list.xml`,  
    `activity_create_routine.xml`, `activity_routine_detail.xml`, `activity_select_exercise.xml`, `activity_exercise_detail.xml`  
    위 액티비티에 해당하는 layout file.
  
- `AndroidManifest.xml`: activity 등록 / app 권한설정 등

## 데이터 출처 및 수정

본 프로젝트에서 사용된 운동 데이터는 [공공데이터포털](https://www.data.go.kr/data/15068730/fileData.do#/tab-layer-openapi)에서 제공된 데이터를 기반으로 하였으며, 프로젝트에 맞게 데이터 구조와 내용 일부를 수정하였습니다.  
수정된 데이터는 운동 유형, 에너지 소비량 등의 속성을 포함하며, SQLite 데이터베이스에 저장하여 앱에서 활용하도록 최적화되었습니다.

