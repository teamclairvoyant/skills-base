<h1 align="center">Welcome to skills-base 👋</h1>

### Description.
1) The objective behind this application is to maintain the skillsets and proficiency level of 
all CV employees at a centralized location. CRUD operations along with reports will help management 
and People Success Team to understand employees’ competency levels and to guide them for the next 
levels (also to utilize them over projects)
2) A skills matrix is a framework used to map employees' skills and their levels. It's a grid that 
  contains information about available skill and their evaluation. It is used to manage, plan, and 
  monitor existing and desired skills for a role, team, department, project, or an entire company.

Wiki pages to know more about the Skill Matrix Application
- [Skill matrix](https://clairvoyant.atlassian.net/wiki/spaces/IndiaTeam/pages/2675507201/Skill+matrix)
- [Skill Matrix Application](https://clairvoyant.atlassian.net/wiki/spaces/IndiaTeam/pages/2620850221/Skill+Matrix+Application)

### Required setup to run locally.
- JDK 11
- IntelliJ IDEA
- Maven
- MySQL DB

### Steps how to run service locally using IntelliJ IDE. 
To run the application locally follow the below steps:
1) Open intelliJ Idea
2) Go to file >> New >> Project from Version Control
3) In the URL section, enter the Skill-Base repository link (https://github.com/teamclairvoyant/skills-base.git).
4) Click on clone button.
5) Once the project is clone, open the ClariseMainApplication class.
6) Run the application from main class

### Build and Start without IDE.

To clone the project and build locally:

```
git clone https://github.com/teamclairvoyant/skills-base.git 
cd skills-base/ClariseMain
mvn clean install
```

Now, we're ready to launch the service.
```
java -jar skills-base/ClariseMain/target/Clarise-0.0.1-SNAPSHOT.jar
```

This will start the service on default port of 8080 connected to local DB.
### To Stop
To gracefully stop, `ctrl+c`

### Link how to access swagger ui locally:
To access swagger UI locally we can use below link
(http://localhost:8080/swagger-ui.html)
