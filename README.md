# Dining Review API project

## Overview
This is a portfolio project with API scenario designed by Codecademy. 
<br />
The project objectives are listed below:
- Construct a RESTful web API with data persistence using Spring and Spring Data JPA
- Use Spring Initializr to generate the initial Java project
- Configure application properties for certain dependencies, including the H2 embedded database
- Define the entities that comprise this application scenario
- Define the repositories that enable creating, updating, and querying these different entities
- Define the API contracts that will enable this application scenario
- Leverage the convenience of Lombok
- Use cURL to test your API scenarios

## Testing
- Find restaurant by its unique ID number<br />
``curl http://localhost:3001/restaurant/1``
- Find user by its username<br />
``curl http://localhost:3001/user/search?userName=ken11``
- Get all dining reviews<br />
``curl http://localhost:3001/dining-review``