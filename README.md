# Api Reactor Project

Api with reactor project implementation, creating controllers based on annotations and functional programming.

1. Clone project:
```
git clone https://github.com/dabliuw22/sb-reactive-api.git
```

2. Run MongoDB with docker:
```
docker pull mongo
docker container run -d --name mongodb -p 27017:27017 mongo
```

3. Run project.

4. Create user:
```
curl -X POST \
  http://localhost:8080/users \
  -H 'Content-Type: application/json' \
  -d '{
	"id": "U1",
	"name": "User1"
}'
```

5. Find by user id:
```
curl -X GET \
  http://localhost:8080/users/U1 \
  -H 'Content-Type: application/json'
```