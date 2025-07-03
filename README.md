# 🎓 Certi

> “자격증, 나에게 딱 맞는 시작을 Certi로!”

자격증 고민부터 이력서 완성까지, 한 번에 도와주는

###  ✨ 취준생을 위한 올인원 자격증 서비스 ✨

#### ✅ 맞춤 자격증 추천
내 전공, 진로, 관심 분야 기반으로 지금 필요한 자격증을 추천!
#### ✅ 공부법 & 일정까지 한 번에
시험 일정, 준비 팁, 학습 자료까지 모두 제공
#### ✅ 스펙 자동 정리
쌓아온 자격증과 경험을 한눈에 정리하고, 목표 직무에 맞게 추천까지 연결

## 👤 Contributors

| <img src="https://avatars.githubusercontent.com/MinseoSONG" style="width:120px; aspect-ratio:1 / 1; object-fit:cover; border-radius:10px;"> | <img src="https://avatars.githubusercontent.com/Jyunee54" style="width:120px; aspect-ratio:1 / 1; object-fit:cover; border-radius:10px;"> | <img src="https://avatars.githubusercontent.com/mjth1s1s" style="width:120px; aspect-ratio:1 / 1; object-fit:cover; border-radius:10px;"> | <img src="https://avatars.githubusercontent.com/kimjw2003" style="width:120px; aspect-ratio:1 / 1; object-fit:cover; border-radius:10px;"> |
|:-----------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------:|
| [송민서](https://github.com/MinseoSONG) | [이지현](https://github.com/Jyunee54) | [김민지](https://github.com/mjth1s1s) | [김종우](https://github.com/kimjw2003) |
| `소셜로그인`<br>`온보딩`<br>`검색` | `이력서` | `홈` | `자격증` |

## 🛠️ Development Environment

| 항목 | 내용 |
|------|------|
| **IDE** | Android Studio Meerkat (2024.3.2 Patch1) |
| **Language** | Kotlin (v2.0.21) |
| **Architecture** | MVVM + Clean Architecture |
| **UI Framework** | Jetpack Compose |
| **Module 구조** | Single Activity / Single Module |
| **Navigation** | Jetpack Navigation |
| **Dependency Injection** | Dagger-Hilt |
| **Async** | Coroutine, Flow |
| **Network** | Retrofit, OkHttp |
| **Design System** | Material3 |
| **Build Tooling** | Gradle Version Catalog |
| **Data Handling** | Repository Pattern |


### 💡 기술 선정 이유

#### 1️⃣ MVVM
1. **관심사 분리**  
   - UI, 데이터 처리, 비즈니스 로직을 분리해 각 컴포넌트의 책임이 명확해지고, 테스트 및 유지보수 효율이 높아집니다.  
2. **View ↔ ViewModel N:1 대응**  
   - ViewModel 재사용성이 높아져 보일러플레이트 코드 감소에 효과적입니다.


#### 2️⃣ Repository Pattern
1. **데이터 소스 추상화**  
   - 네트워크 및 로컬 DB 등 다양한 데이터 소스를 통합적으로 관리할 수 있어 데이터 접근 로직을 분리할 수 있습니다.  
2. **유지보수 및 확장성 향상**  
   - 변경 사항을 Repository 레이어에 국한시켜 전체 구조에 영향을 주지 않으므로, 유지보수성이 높습니다.


#### 3️⃣ Dagger-Hilt
1. **통일된 DI 패턴 제공**  
   - 일관된 의존성 주입 방식을 통해 협업 시 코드 가독성과 이해도를 높일 수 있습니다.  
2. **Android 환경 최적화**  
   - Android 컴포넌트에 맞춘 주입 어노테이션을 제공하여 개발 효율성을 높입니다.


#### 4️⃣ Clean Architecture
1. **비즈니스 규칙의 명확한 분리**  
   - 	이번 프로젝트는 iOS, 서버 등 다양한 플랫폼과 병행 개발이 이루어지므로, 비즈니스 로직을 외부 변화(플랫폼, UI 등)로부터 독립시키는 Clean Architecture가 더 적합합니다.
   - 	도메인 계층이 선택적인 구글 권장 아키텍처보다, 도메인을 중심에 두는 구조가 명확한 책임 분리에 유리하다고 판단했습니다.
2. **변경에 유연하게 대처 가능**  
   - 계층 간 의존성을 추상화해 느슨한 결합 구조로 구성되어, 스프린트 과정에서 발생할 수 있는 다양한 변경 사항에 유연하게 대응할 수 있습니다.


## 📄 Convention
🔗 [컨벤션 문서 보러가기](https://tremendous-baryonyx-347.notion.site/2175e9d69a2681a28680d587454d7d80?source=copy_link)

- Code Convention
- Git Convention
- Issue & PR Convention
- Code Review convention


## 🗂️ Project Structure
```Text
📂 org.sopt.certi
┣ 📂 data
┃ ┣ 📂 local
┃ ┃ ┣ 📂 datasource
┃ ┃ ┣ 📂 datasourceimpl 
┃ ┣ 📂 mapper
┃ ┃ ┣ 📂 todata
┃ ┃ ┣ 📂 todomain
┃ ┣ 📂 remote
┃ ┃ ┣ 📂 datasource
┃ ┃ ┣ 📂 datasourceimpl
┃ ┃ ┣ 📂 dto
┃ ┃ ┃ ┣ 📂 base
┃ ┃ ┃ ┣ 📂 request
┃ ┃ ┃ ┣ 📂 response
┃ ┃ ┣ 📂 service
┃ ┃ ┣ 📂 util
┃ ┣ 📂 repositoryimpl
┣ 📂 di
┣ 📂 domain
┃ ┣ 📂 model
┃ ┣ 📂 repository
┃ ┣ 📂 type
┃ ┣ 📂 usecase
┣ 📂 presentation
┃ ┣ 📂 ui
┃ ┃ ┣ 📂 splash // 스플래쉬
┃ ┃ ┣ 📂 home // 홈
...
┃ ┣ 📂 util
┣ 📂 ui.theme
```
