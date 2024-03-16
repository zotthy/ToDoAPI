
# Project ToDOAPI

# Used programs
- IntelliJ IDEA 2022.3.3
- DataGrip
- Postman 2
- Docker

# Project is created with:
- Java 17
- Spring boot 3.3.0 M1
- Hibernate
- mySQL  8.2.0

# How to run
git clone https://github.com/zotthy/ToDoAPI.git


cd ToDoAPI


docker compose up -d  <- run database


/mvn:spring boot run

# API Request

Register localhost:8080/auth/register POST


    {
        "name": "Sebastian",
        "surname": "Szklanka",
        "email": "admin@admin.pl",
        "password": "Bardzotrudnehaslo123"
    }


Login localhost:8080/auth/login POST


	{
    	"username":"admin@admin.pl",
    	"password":"Bardzotrudnehaslo123"
	}
return token

Profile localhost:8080/profile GET, add token autorization


	{
    	"id": 21,
    	"name": "Sebastian",
    	"surname": "Szklanka",
    	"email": "admin@admin.pl",
    	"address": {
        	"id": 21,
        	"street": "1818 Oak St",
        	"city": "Detroit",
        	"zip_code": "48201"
    	}
	}


Add task localhost:8080/addTask POST, and add token autorization


	{
    	"title":"nuaczyc sie do sprawdzianu",
    	"description":"calkowanie",
    	"due_date":"2024-03-25",
    	"priority":"High",
    	"status":"IN progres",
    	"category_id": 2
	}

List my task localhost:8080/tasks?page=0&size=3 GET, and add token autorization
   ```json
 	[
            	{
                "id": 21,
                "title": "nuaczyc sie do sprawdzianu",
                "description": "calkowanie",
                "due_date": "2024-03-25",
                "priority": "High",
                "status": "IN progres",
                "category": {
                    "id": 2,
                    "name": "Personal"
                }
            },
            {
                "id": 22,
                "title": "przygotowac sie do prezentacji",
                "description": "preznetaca",
                "due_date": "2024-03-24",
                "priority": "High",
                "status": "IN progres",
                "category": {
                    "id": 1,
                    "name": "Work"
                }
            }
        ]
    
    
```


Detials task localhost:8080/tasks/{id}  GET, and add token autorization



```json
[
    {
        "id": 22,
        "title": "przygotowac sie do prezentacji",
        "description": "preznetaca",
        "due_date": "2024-03-24",
        "priority": "High",
        "status": "IN progres",
        "category": {
            "id": 1,
            "name": "Work"
        }
    }
]
```
