# 9OODS

## Table of Contents
- [프로젝트 개요](#project-outline)
- [개발환경](#development-environment)
  - [Architecture](#architecture)
  - [Backend](#backend)
  - [Database](#database)
  - [InfraStruture](#infrastructure)
  - [Monitoring](#monitoring)
- [ERD](#erd)
- [API 명세서](#api)
- [프로젝트 스케쥴](#schedule)

---

<h3 id="project-outline">
<img src="src/main/resources/image/project_outline.png" width="15">
프로젝트 개요
</h3>
<img src="src/main/resources/image/9oods_main.png" alt="프로젝트 메인 페이지" width="300">

- 프로젝트 명 : 9OODS
- 소개
  - 9OODS는 유튜버, 캐릭터, 연예인 등 모든 크리에이터의 굿즈를 사고 팔 수 있는 이커머스 서비스 입니다.
  - 9OODS는 일반 유저(구매자)와 셀러(판매자)로 나눠집니다.
  - 구매자는 상품 또는 브랜드를 검색할 수 있고, 상품을 검색 및 조회할 수 있습니다.
  - 셀러는 상품 등록, 수정, 삭제, 판매된 상품 조회, 기간 내 판매된 총 금액 및 많이 판매된 상품 탑텐을 조회할 수 있습니다.

---

<h3 id="development-environment">
<img src="src/main/resources/image/Development_environment.png" width="15">
개발환경
</h3>

##### Architecture
<img src="src/main/resources/image/architecture.png" alt="Architecture" width="100%">

##### Backend

- JDK 17
- SpringBoot 3.2.X
- SpringSecurity 3.2.X
- ElasticSearch 8.13.X

##### Database

- MySQL 8.0.X
- Redis 7.0.X

##### InfraStructure

- GCP ComputeEngine
- Docker
- GitHub Actions

##### Monitoring

- Kibana 8.13.X

---

<h3 id="erd">
<img src="src/main/resources/image/erd.png" width="15">
ERD
</h3>
<img src="src/main/resources/image/9oods_erd.png" alt="ERD">

---

<h3 id="api">
<img src="src/main/resources/image/api.png" width="15">
API 명세서
</h3>
<details>
<summary> 펼치기 </summary>
<img src="src/main/resources/image/user_api.png" alt="user_api">
<img src="src/main/resources/image/seller_api.png" alt="seller_api">
<img src="src/main/resources/image/product_api.png" alt="product_api">
<img src="src/main/resources/image/order_api.png" alt="order_api">
<img src="src/main/resources/image/coupon_api.png" alt="coupon_api">
</details>

---

<h3 id="schedule">
<img src="src/main/resources/image/schedule.png" alt="프로젝트 스케쥴" width="15">
프로젝트 스케쥴
</h3>
<img src="src/main/resources/image/project_schedule.png" alt="프로젝트 스케쥴" width="500">
