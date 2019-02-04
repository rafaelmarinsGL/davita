# Davita - Licensure and Certification QUESTIONNAIRE

Physician Experience

Install Gradle (MACOS):  
brew install gradle

Download Postman:  
https://www.getpostman.com/downloads/ 

H2 Database:  
http://localhost:8080/h2  
user:sa  
pass:  

# Examples:  

POST  
localhost:8080/questionnaire  
application/json  

{
    "content": "New Content",
    "submitDate": null,
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

GET  
localhost:8080/questionnaire/{id}  


