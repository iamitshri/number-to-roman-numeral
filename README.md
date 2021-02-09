
## How to package
- mvn clean install


## How to test
- http://localhost:8080/v1/romannumeral?query=1
- [Swagger](http://localhost:8080/swagger-ui.html)

## How to build Docker Image
- docker build -t numer-to-roman/number-to-roman-numeral .
- docker run -p 8080:8080 numer-to-roman/number-to-roman-numeral
- Using build packs
  - ./mvnw spring-boot:build-image

## How to run with newrelic agent
- java -javaagent:"/FULL_PATH/number-to-roman-numeral/newrelic/newrelic.jar"  -jar target/number-to-roman-numeral-0.0.1-SNAPSHOT.jar

## Checklist
- Deployment diagram using lucid chart
- Method comments
- Exception
- Controller endpoint version
- References

## Steps
- Create Cluster, Target, LEB, SG etc
- Create Task Definition
- Create Github hook
- Create buildspec.yml to build image and upload to ECR Repo
- Create ECR Repo
- Create Dockerfile to create an image with new relic integration
  
## Design decisions
- Interface is created for Integer to Roman conversion
  - In the future, adhering to Open Closed Principle, we can swap out implementations
- Lombok could be used for some simplicity, 
  - Pros:
    - Boiler plate code is reduced
  - Cons:
    - Engineers who aren't familiar with lombok could see a bit higher ramp up time.
    - Lombok has to keep up with IDE upgrades and latest java changes, so could be a problem  
- Version is added to rest endpoint for safer future upgrades
- For controller testing, Mockito is used to mock service class, to test controller independently

## References
- https://en.wikipedia.org/wiki/Roman_numerals
- https://reflectoring.io/spring-boot-docker/
- https://spring.io/blog/2020/01/27/creating-docker-images-with-spring-boot-2-3-0-m1