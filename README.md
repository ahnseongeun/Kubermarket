# kubermarket

중고거래 애플리케이션(with 당근마켓 토이 프로젝트)

프로젝트 소개 
쿠버네티스를 이용하여 프라이빗 클라우드와 CI/CD환경을 만들고 MSA기반 
클라우드 네이티브 애플리케이션을 개발할 수 있는 PaaS 플랫폼을 구축하려 합니다.
쿠버네티스와 AWS를 활용해서 클라우드 환경에서 직접 클러스터를 구현하고 분산처리를 할수 있고 
Gitlab을 활용한 테스트, 빌드, 배포를 자동화 기능을 이용해서 인프라, 배포, 운영, 모니터링 환경을 구성할 수 있는 마이크로 단위의 중고거래웹을 개발하려고 합니다.

프로젝트 개발 기간
2020.6 ~ 2020.10

프로젝트 주요기능
* 마이크로 서비스간의 통신을 통한 느슨한 결합도 및 쿠버네티스 클러스터(미통합)
* 쿠버네티스 서비스, 디플로이먼트를 간편하게 조작하고 현황을 볼 수 있는 리소스 모니터링(미통합)
* JWT기반의 서비스 인증시스템
* 상품등록 및 조회, 검색기능
* 유저간의 채팅기능(미통합)

 프로젝트 아키텍처
 
 ![전체적인 아키텍처](https://user-images.githubusercontent.com/47744119/100739888-25ed4480-341b-11eb-9ea6-efd7c231dba1.png)

 프로젝트 ERD
 
 ![중고거래 어플리케이션 ERD](https://user-images.githubusercontent.com/47744119/100739806-05bd8580-341b-11eb-81f6-48f3dbf23286.png)
 프로젝트에 사용된 기술 스택
 * Kubernetes
 * Gitlab CI/CD
 * Docker
 * Spring boot
 * Node.js
 * nginx
 * Mysql
 
 내가 프로젝트에서 기여한 부분
 * Spring boot를 이용한 User, Product Backend 개발
 * Gitlab CI/CD 
 * Gitlab private Registry 구축하기 위한 SSL을 적용한 HTTPS 통신
 * nginx을 이용한 redirec_http_to_https 적용 
 * Dockerfile 및 docker-compose.yaml, gitlab-ci.yml를 이용한 배포
 
 Swagger 적용
 
 * Admin - 백도어 기능으로 필요한 데이터를 조작
 ![숨겨진 admin 기능 백도어](https://user-images.githubusercontent.com/47744119/101281795-9ed20f00-3814-11eb-83eb-08e4b26197b7.PNG)
 
 * Customer - 실제로 사용할 사용자
 ![customer](https://user-images.githubusercontent.com/47744119/101281786-8f52c600-3814-11eb-9c8a-d9a6f5377aad.PNG)
 
 * login - Token을 이용한 로그인 기능
 ![로그인](https://user-images.githubusercontent.com/47744119/101281794-9c6fb500-3814-11eb-9280-771a1b368adc.PNG)

 
 
 
 




