# 9OODS

## Table of Contents
- [프로젝트 개요](#프로젝트-개요)
- [개발환경](#development-environment)
  - [Architecture](#architecture)
  - [Backend](#backend)
  - [Database](#database)
  - [InfraStruture](#infrastructure)
  - [Monitoring](#monitoring)
- [ERD](#erd)
- [API 명세서](#api-명세서)
- [프로젝트 스케쥴](#프로젝트-타임라인)

## 프로젝트 개요
<img src="src/main/resources/image/9oods_main.png" alt="프로젝트 메인 페이지" width="300">

- 프로젝트 명 : 9OODS
- 소개
  - 9OODS는 유튜버, 캐릭터, 연예인 등 모든 크리에이터의 굿즈를 사고 팔 수 있는 이커머스 서비스 입니다.

---

<h2 id="development-environment">
<img src="src/main/resources/image/Development_environment.png" width="15">
개발환경
<img src="src/main/resources/image/Development_environment.png" width="15">
</h2>

##### Architecture
<img src="src/main/resources/image/architecture.png" alt="Architecture" width="100%">

##### Backend

- JDK 17
- SpringBoot 3.2.X
- SpringSecurity 3.2.X
- ElasticSearch 8.13.X

##### Database

- MySQL 8.0.X
- Redis 3.0.X

##### InfraStructure

- GCP ComputeEngine
- Docker
- GitHub Actions

##### Monitoring

- Kibana 8.13.X

---

## ERD
<img src="src/main/resources/image/9oods_erd.png" alt="ERD">

---

## API 명세서
<details>
<summary> 펼치기 </summary>
<img src="src/main/resources/image/user_api.png" alt="user_api">
<img src="src/main/resources/image/seller_api.png" alt="seller_api">
<img src="src/main/resources/image/product_api.png" alt="product_api">
<img src="src/main/resources/image/order_api.png" alt="order_api">
<img src="src/main/resources/image/coupon_api.png" alt="coupon_api">
</details>

---

## 프로젝트 타임라인
<img src="src/main/resources/image/project_schedule.png" alt="프로젝트 스케쥴" width="500">
