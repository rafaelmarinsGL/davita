# Davita - Licensure and Certification QUESTIONNAIRE

Physician Experience

Install Gradle (MACOS):  
brew install gradle

Clone project and:  
run command: gradle bootRun  

Download Postman:  
https://www.getpostman.com/downloads/ 

H2 Database:  
http://localhost:8080/h2  
JDBC URL: jdbc:h2:~/questionnaire  
user:sa  
pass:

Swagger Documentation:
http://localhost:8080/swagger-ui.html

Actuator:  
http://localhost:8080/actuator  

# Examples:  

POST  
localhost:8080/api/v1/questionnaire  
application/json  

```json
{
    "content": "New Content",
    "submitDate": "2018-02-04T00:00:00",
    "person": {
        "firstName": "Rafael",
        "middleName": "Marins",
        "lastName": "Carinha",
        "suffix": "Mr.",
        "ssn": 111111111,
        "birthDate": "01/01/1981",
        "birthPlace": "Santos",
        "birthCountry": "Brazil",
        "address": {
            "street": "Main street 1",
            "city": "Alajuela",
            "state": "San Jose",
            "zip": 111111
        }
    }
}
```

GET   
localhost:8080/api/v1/questionnaire/{id}   

# TODOs  

-Add Swagger Documentation (done)  
-Add Mockito Unit Test (done)  
-Add PUT/DELETE methods (done)  
-Add ListAll methods (done)  
-Add Docker YAML 
-Add Error Handling - ControllerAdvice (done)  
-Add SonarQube   
-Add SpringBoot Actuator (done)  
-Add Questionnaire status(Pending/Reviewed/Completed...)   
-Add Questionnaire versioning   
-Add Searches (by person/version/date...)  
-Add authentication/permission   


