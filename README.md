----HomeChef-----
My project uses 3 tier architecture with react js as frontend, springboot rest api as backend, MySQL as database. The frontend communicates with backend using axios, the backend follows controller service repository layers and jpa/Hibernate handles orm with mysql.

a. Frontend (presentation layer):
Built with react js. Users interact through UI pages like login, register, menu display, booking page, billing page, admin dashboard. It uses axios to call backend APIs. Ex: when user books a chef, react sends a post request to /api/ bookings. 
b. Backend(business layer):
Developed using springboot. It exposes rest APIs for user management, chef management, menu management, booking,billing. It follows a layered structure i.e controller layer -- handles api request. Service layer -- contains business logic ie validating booking, generating bills.
c. Db( data layer): 
MySQL used as relational databases. Entities are: user, chef, menu,booking, billing, admin. Relationship: one user -- many bookings , one chef -- many bookings , one bookings -- one bill.
