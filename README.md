# wetsuit_hub
A web app to find the best deals on wetsuits across a number of different sites

Backend is a web scraper that takes wetsuits from 7 different wetsuit sellers and saves these to a MariaDB Database which can be accessed by a RESTful API.

Frontend is a basic 2 page React App that sends requests to the API to display wetsuits sorted by a number of things such as wetsuit price or thickness clicking on a wetsuit tile will take you to the parent site. 

Credit to Tegan Ward for the Background Images. 

You can see a release of the code at thewetsuithub.co.uk which is hosted on AWS using an Amplify App frontend and the backend running on an EC2 instance using an RDS database. 
