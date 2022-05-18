1. Install Docker
2. Enter Folder "DummyTimetableBoot"
3. Execute "docker-compose up -d" to start all services
4. Test your API by Postman
   a. Use HTTP POST http://localhost:8080/api/users with Request Body 
      {
          "name": "John"
      }
	  to add a new user
   b. Use HTTP GET http://localhost:8080/api/users to get all users
   c. Use HTTP POST http://localhost:8080/api/users/1/time-slots with Body
	  {
		"start_at": 1652578657285,
		"end_at": 1652578658285
	  }   
      to add a time for a user id = 1
5. Execute "docker-compose down" to stop all services