### 
### Maven 
### Java
### inteliJIDEACommunityEditon
### Chrome/Firebox
### node/np
###


### h3 How to run docker container
docker pull selenium/standalone-chrome@latest     
docker run --rm -d -p 4445:4444 -v /dev/shm:/dev/shm selenium/stan    

### h3 How to run tests
mvn clean test

### h3 allure reports
allure generate target/allure-results --clean 