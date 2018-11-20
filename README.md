# AdidasCodeChallenge

## Running

The easiest way to run the components is to use [Docker compose](https://docs.docker.com/compose/)

>NOTE: First build might take a bit longer to deploy because the image layers need to be downloaded.

````
docker-compose up -d
````


## Usage

When services have been deployed using docker-compose, open a Postman instance and POST a subscription in `[DOCKER_HOST]/subscription/` in to see how the service works. To ge your DOCKER_HOST execute next:

````
$ echo $DOCKER_HOST
````
To fill a correct subscription in request body to be sent, here you are an example:

````
{
    "email": "sergiomarnez@gmail.com",
    "firstName": "Sergio",
    "gender": "M",
    "dateOfBirth": "1985-06-17",
    "consent": 1,
    "newsletterId": 4654654
}
````
[[/resources/postman.jpg|alt=Postman request example]]

Swagger definition is in next [link](/AdidasSubscriptionService/swagger.yaml)

Result will be subscription ID generated. Subscription is inserted in a MongoDB test database.

It possible to check MongoDB content using any MongoDB management tool. I used [Robo 3T](https://robomongo.org/), but any other or using console directly is enough to check MongoDB content.

[[/resources/robo3T.jpg|alt=Inserted subscription]]

## CI/CD proposal

###Tools:

Jenkins - CI/CD - NOT IMPLEMENTED To do in a SIT/UAT/PROD environment
Git/GitHub - Source Control Management
Docker - Container
Maven — Build tool
Docker Compose - Run and define Docker apps

Each service is a simple web service written in Java with the Spring Boot framework.
Maven is the build tool and GitHub the source code repository.
Each commit to GIT repo is able to trigger a Jenkins build, using Maven to compile the code, run tests and so on.

###System Schema

[[/resources/schema2.jpg|alt=Application built schema]]

[[/resources/schema2.jpg|alt=Running schema]]

## Improvements:

There are some improvements that should be made here:
- **Scalation**: Include a new HAProxy service taking it directly from Docker HUB. It provides flexible caching capabilities, can be used as a reverse proxy and as load balancer. 
- **Testing**: Include testing for the three services.
- **Caching strategy**: Caching inside the `product-review` app is being emptied each hour and updated along with other CRUD operations. A more subtle strategy should be used, depending on how often the data might change and the importance of having the fresh data in real time.
- **Security**: No security has been implemented so, it's easy to improve it. It's possible to add spring security in Email and Events service (fe Spring Security) and also authentication in MongoDB as some basic measures.
- **Error handling** No logging nor error handling has been implemented. 
- And finally, always it's possible to improve the code 
