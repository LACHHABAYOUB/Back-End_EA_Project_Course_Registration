# Attendance Checking Software
Attendance Checking Software is back End App for check Meditation Attendance Project 


App Build with Spring Framework as Back-End.



1 Getting Started

```sh
1.1 The Goal

The goal of this project is to give you hands-on, practical experience with going through all 4 phases of
SDLC (Software Development Lifecycle) for a real-world project. You will all have a chance to go through
Inception, Elaboration, Construction and Transition. 

```

```sh
1.2 Attendance Checking Software

We are trying to create a web application that will automate the process of creating attendance reports
for students, faculty and administrators.
 Students need to be able to login and view the list of courses that they have taken (or are
currently taking) and view their daily meditation attendance for that course.
 Faculty need to be able to login and view a list of courses that they are or have been teaching
for the past six months and view a class list along with attendance report for each student in
that class.
 Personnel need to be able to login and search for any student and view their all-time attendance
records. They should also be able to change any record (manually change an “absent” to
“present”) for any date.
 Administrators need to be able to login and assign roles. Administrators also have the same
privileges that personnel have.
 Users of this system can have multiple roles. For example a faculty can also be Personnel! 

```


```sh
1.3 The Requirements

The system is based on a bar-code reader (similar to the one in use at the Dalby Hall for morning
meditations).
 The barcode reader creates a record of each scan by saving the bar-code ID, date, timeslot
(morning or afternoon) and location.
 Each student has an ID (000-xx-yyyy), first name, last name and a bar-code ID.
 Each timeslot has an abbreviation (such as AM, PM) which can be used as natural ID,
description, begin time and end time (which mark the beginning and end of that timeslot or
times during which scans are accepted)
 Each location has an ID and description
 Each student can sign up for one more course offerings
 Each course offering has id, Course Id, start and end dates and marks a particular offering of a
course
 Each course can be offered many times and has an ID, name and description
 Each course offering also can have multiple sessions
 Each “session” is a timeslot offered at a particular date for a particular course. So each “session”
record has an ID, Course Offering ID, Timeslot ID and date. 

```

2 Prerequisites

```sh

2.1 Java JDK
https://www.oracle.com/java/technologies/javase-downloads.html

2.2 Integrated development environment I suggest intellij or Eclipse for backend and WebStorm Or Visual Studio Code For the front-end . 

Download intellij:
https://www.jetbrains.com/idea/download/

Download Eclipse:
https://www.eclipse.org/downloads

Download Webstorm
https://www.jetbrains.com/webstorm/download/#section=windows

Download Visual studio code:
https://code.visualstudio.com/download

2.3 Database Management i suggest MySql . 

Download MySql:
https://www.mysql.com/downloads/

```


3 Installing



3.1 Install the required Prerequisites
```sh
1- Import the back-end and go to src\main\resources\application.properties
-   In application.properties you should replace 
    spring.datasource.username=YOUR USERNAME
    spring.datasource.password=YOUR PASSWORD
-After that you can run the project 


```


4 Project Team

```sh
Ayoub Lachhab    
Hai Dang Nguyen  
Sanjeevan Sigdel
Gedeon Niyonsenga  
Alba Elizabeth Padilla Pacheco
```

5 Requirement 

```sh
https://youtu.be/UCtDLcTTwzw
```



6 Duration of work

```sh
4 days
```

7 Copyrights
```sh
Copyrights Maharishi international university ©2021 All Rights Reserved
```
