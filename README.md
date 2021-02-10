## [Reference a specification for Roman numerals online](#roman)
## [How to build and run your project.](#how-to-run)
## [Engineering and testing methodology](#methodology)

## [Your packaging layout](#deployment-dia)
## [API Documentation](#swagger)
## [Tests](#tests)
## [Error Handling](#error)

## Deployment Diagram<a name="deployment-dia"></a>

![Deployment Diagram](images/deployment-diagram.svg)

- Code Pipeline
  - https://us-west-2.console.aws.amazon.com/codesuite/codepipeline/pipelines/number-to-roman/view?region=us-west-2#

- Sonar Cloud Dashboard
  - https://sonarcloud.io/dashboard?id=iamitshri_number-to-roman-numeral&branch=main

## Tests 
- Unit Tests
  - Service class is tested for all the  valid input range
    - Valiadation for Integer to Roman 
      - Test Data: Excel file is created with number in range [1-3999] to roman representation
      - Integer to Roman integer implementations is validated by reading this file and comparing actual vs expected
  - Controller is independently unit tested by mocking service class 
- Integration Tests
  - Controller based integ Test [TODO]
  - Application Context initialization is checked


## Swagger Documentation <a name="swagger"></a>
- [Swagger Link](http://number-to-numeral-load-balancer-1676602525.us-west-2.elb.amazonaws.com/swagger-ui.html)

## Prerequisites
- Setup JDK 11,Maven and Docker
- Intellij or Eclipse
- AWS and NewRelic account is accessible

## [How to run](#how-to-run)

## Local
  - ```git clone https://github.com/iamitshri/number-to-roman-numeral.git```
  - ```cd number-to-roman-numeral```
  - ```./mvnw spring-boot:run```
  - ``` curl 'http://localhost:8080/v1/romannumeral?query=1'```

  ### Running with newrelic locally
      mvn clean install
      java -javaagent:"/absolute-path/number-to-roman-numeral/newrelic/newrelic.jar" -jar target/number-to-roman-numeral.jar
  ## AWS
 
  #### Check the version deployed in AWS ECS
    ````
    curl 'http://number-to-numeral-load-balancer-1676602525.us-west-2.elb.amazonaws.com/v1/romannumeral?query=3434'
    ````  
  ### How to build Docker Image
  - ```docker build -t numer-to-roman/number-to-roman-numeral .```
  - ``` docker run -p 8080:8080 numer-to-roman/number-to-roman-numeral ```
    - Stop the docker image
      - ```docker container ls```
      - ```docker stop containerId```
  - Optional Using build packs
    - ./mvnw spring-boot:build-image

## Engineering and testing methodology<a name="methodology"></a>
### Steps
- Created video demonstration of these resource creation
  - https://youtu.be/IVayX7sOZcM
- Create Dockerfile to create an image with new relic integration
- ECR: Container registry
- Create buildspec.yml to build image and upload to ECR Repo
- Code Build to run the build process
- Target group
- Load balancer
- ECS 
  - Task Definition
  - Cluster
  - Service
- Code Pipeline to automate build and deploy

### Design decisions
- Interface is created for Integer to Roman conversion
  - In the future, adhering to Open Closed Principle, we can swap out implementations
- Version is added to rest endpoint for safer future upgrades
- For controller testing, Mockito is used to mock service class, to test controller independently

## References
### Roman specification <a name="roman"></a>
- https://en.wikipedia.org/wiki/Roman_numerals
## Docker Image creation
- https://spring.io/blog/2020/01/27/creating-docker-images-with-spring-boot-2-3-0-m1
- [Docker installation https://docs.aws.amazon.com/AmazonECS/latest/developerguide/docker-basics.html](https://docs.aws.amazon.com/AmazonECS/latest/developerguide/docker-basics.html)
- https://docs.aws.amazon.com/codebuild/latest/userguide/sample-docker.html
## Building CICD
- https://docs.aws.amazon.com/codepipeline/latest/userguide/ecs-cd-pipeline.html
## New Relic Log ingestion
- https://serverlessrepo.aws.amazon.com/applications/us-east-1/463657938898/NewRelic-log-ingestion
