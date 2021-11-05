# carRentals

The app is basically two microservices with each a clear mission:
1- car-rentals-persistence: the microservice that communicates directly with the database (read and write), it also creates a H2 database in runtime and populates it with some data.
2- car-rentals: the microservice that contains the business logic. 

#how to run the project: 
1-build each microservice separately with command mvn clean install
2-run each microservice as a spring boot app.

#how to visualize the dataBase:
  run persistence microservice, then navigate to http://localhost:33389/h2-console/
#how to test the app:
  There is a collection of postman tests in car-rentals repo, it can be imported and run on postman.
  

