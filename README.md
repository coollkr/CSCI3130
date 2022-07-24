# CSCI 3130 - Software Engineering (Summer 2022)


# NOTE: DAL SERVERS WENT DOWN as we were doing our last few tests, so we are unable to confirm that our last commit removing the .iml file works as flawlessly as last night's demonstration... 


* Date Created: 2022 07 07
* Date Modified: 2022 07 22


# Team



* Team Name: Group 15
* Team Members:
* (B00856552) | Tariq Ferwa | fr830827@dal.ca
* (B00843752) | Katie Arsenault | kt514867@dal.ca
* (B00752864) | Taylor MacIntyre | ty452018@dal.ca
* (B00861127) | Kairui Liang | kr495028@dal.ca
* (B00831244) | Yuejia Zhang | yj998768@dal.ca


# Project



* Project Name: Trello Application
* Project Description: A web based trello application clone build using ReactJS and Spring Boot
* Inception Year: 2022


# Links



* [https://git.cs.dal.ca/courses/2022-summer/csci-3130/projects/group15/-/tree/main](https://git.cs.dal.ca/courses/2022-summer/csci-3130/projects/group15/-/tree/main) 


# Citations

Formik for password validation

Date accessed: June 24, 2022

URL: [https://formik.org/docs/overview](https://formik.org/docs/overview) 

Yup-password for password validation

Date accessed: June 24, 2022

URL: [https://www.npmjs.com/package/yup-password](https://www.npmjs.com/package/yup-password) 

Yup for password validation

Date accessed: June 24, 2022

URL: [https://formik.org/docs/tutorial](https://formik.org/docs/tutorial) 

Mockito for mocking the repository

Date accessed: June 25th, 2022

URL: [https://site.mockito.org/](https://site.mockito.org/)

Material UI for styling

Date accessed: June 23, 2022

URL: https://mui.com/ 

React-Dom

Date accessed: June 20, 2022

URL: https://reactjs.org/docs/react-dom.html

React-Router-Dom 

Date accessed: June 20, 2022

URL: https://v5.reactrouter.com/web/guides/quick-start

The start code for board and workspace front end is from the react tutorial 2 section.

Date accessed: June 23th, 2022

URL: [https://git.cs.dal.ca/dashah/csci3130_s22_reacttutorialdemo.git](https://git.cs.dal.ca/dashah/csci3130_s22_reacttutorialdemo.git) 

The starter code for user management front end has been adapted from the React Basics and User Authentication Demo

Date accessed: June 22, 2022

URL: [https://dal.brightspace.com/d2l/le/content/221735/viewContent/3048848/View](https://dal.brightspace.com/d2l/le/content/221735/viewContent/3048848/View) 

Syntax for Hash Mapping to connect front and back end adapted from a resource provided by the TA

Date accessed: June 29, 2022

URL: [https://stackoverflow.com/questions/44839753/returning-json-object-as-response-in-spring-boot](https://stackoverflow.com/questions/44839753/returning-json-object-as-response-in-spring-boot) 

The starter code for users, boards, and workspaces in the backend was taken from both the  Spring-Boot-Backend tutorial video and the in class Sprint tutorial collab-recording

Ongoing access starting from date: June 16th 2022

URL-1: [https://dal.brightspace.com/d2l/le/content/221735/viewContent/3045067/View](https://dal.brightspace.com/d2l/le/content/221735/viewContent/3045067/View)

URL-2: [https://dal.brightspace.com/d2l/le/content/221735/viewContent/3046718/View](https://dal.brightspace.com/d2l/le/content/221735/viewContent/3046718/View)


The week code section of the getTaskWithDate function in TaskService was referenced from the link below

Date accessed: 15th July 2022 

URL: [https://stackoverflow.com/questions/26012434/get-week-number-of-localdate-java-8](https://stackoverflow.com/questions/26012434/get-week-number-of-localdate-java-8)

The syntax to write the native query in the TaskRepository class came from following website:

Date Accessed: July 17th 2022

URL: [https://www.baeldung.com/spring-data-jpa-query](https://www.baeldung.com/spring-data-jpa-query)

# Frontend


# Prerequisites



* npm: 8.5.5 
* node: 16.15
* Download an IDE that runs javascript and node.js (VS Code is a good choice)


# Installation



* npm install (cd to FrontEnd/group-project first) - this installs the node_modules which are not pushed to git


# Running the Frontend



* npm run start (cd to FrontEnd/group-project first)
* Ensure you’re running the front end on localhost: 3000 as that is the port that the back end is listening to


# Backend


# Prerequisites



* Download an IDE of your choice.. 
    * Note: VS Code allows you to run both frontend and back end at the same time, instead of running frontend on VS code and backend on another IDE,
* Connection to remote database
* JDK 17
* Maven 4.0.0  


# Installation



* When opening backend in IDE, ensure you have the correct JDK and maven versions


# Running the Backend



* Connect to remote database using
    * username: csci3130_group15
    * password: EeSo5eiyoh
* IF USING INTELLIJ: Remove the .iml file before opening the project in IntelliJ, if a Load Maven pop-up comes up, click load
* Locate and run the TrelloProjectApplication.java file


# Instructor



* [Dr. Tushar Sharma | tushar@dal.ca](tushar@dal.ca)


# Teaching Assistant



* Rushi Patel | rs397441@dal.ca
