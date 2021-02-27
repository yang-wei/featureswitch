port := 8080
endpoint := localhost:$(port)/feature

run:
	./mvnw clean spring-boot:run


curl-test:
	curl -H "Content-Type:application/json" \
 		"$(endpoint)" \
 		-d '{"featureName":"instacash","email":"user1@gmail.com","enable":true}'
	curl "$(endpoint)?featureName=instacash&email=user1@gmail.com" | \
		grep "{\"canAccess\":true}"
	curl -H "Content-Type:application/json" \
		"$(endpoint)" \
		-d '{"featureName":"instacash","email":"user1@gmail.com","enable":false}'
	curl "$(endpoint)?featureName=instacash&email=user1@gmail.com" | \
		grep "{\"canAccess\":false}"


