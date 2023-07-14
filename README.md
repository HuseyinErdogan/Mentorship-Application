# Mentorship Application
It is a mentoring process tracking web application with users in Admin, Mentor and Mentee roles. Each role has different duties and permissions.
- Admin
  * can make arrangements of subjects. 
  * can positive or negative responses to users' requests to be mentors.
- Mentor
  * can follow the processes IT mentors and make arrangements(phase planning, phase committing, giving point to phase). 
  * can list mentorship requests and give mentorships to users who want to receive mentoring. 
  * can receive mentorship from different mentors.
- Mentee
  * can receive mentorship from mentors
  * can follow the processes IT mentors and make arrangements(phase planning, phase committing, giving point to phase).  
  * can apply to admin to become a Mentor.
## Technologies Used
![Deployment Diagram](docs/Deployment%20Data%20Diagram.png)
## Physical Data Diagram
![Physical Data Diagram](https://github.com/HuseyinErdogan/Mentorship-Application/blob/master/docs/Physical%20Model%20Diagram.png)
### Security
JSON Web Token is used with embedded LDAP authentication. A JSON Web Token is created for the user who provides LDAP authentication and performs operations with this token on the frontend side.
