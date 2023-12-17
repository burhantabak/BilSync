# BilSync

## Team Members

- Tuna Saygın - 22102566
- Hüseyin Burhan Tabak - 22102516
- Ahmet Tarık Uçur - 22102946
- Işıl Özgü - 22102276
- Kanan Zeynalov - 22101007

## Motivation

The motivation is the need for a web application where every Bilkenter can share their thoughts anonymously or publicly.
Not only can they share their opinions, but this app will also be the platform of exchange and solidarity in Bilkent.

## Problem

Bilkent members use many apps to share their thoughts, notify the community, and cooperate.
Our application provides a single platform for Bilkenters to connect in various ways.
Bilkenters use a so-called Instagram confession page where people send messages to the page admin, and the admin posts
the sent messages to a public page.
Bilkenters also use letgo, Facebook, this Instagram page, or Sahibinden to sell their used materials to other users,
probably from Bilkent.
This all-over-the-place communication style sometimes disserves the community and inhibits effective interaction, and
thus, the Bilkent community needs a united page for Bilkenters only in which people can do all of these in one place.

## Features

- Lost & Found feature, where the retrieval process for lost items will be faster and safer.
- A donation as well as a second-hand listing system for books to any other course material from Bilkenters to the
  people in need of them.
- People will be able to message in private rooms, managing communication more quickly than trying to find someone in
  WhatsApp groups for hours.
- Instead of instructors having their inboxes filled with student questions, they can talk with students more freely
  here. Different from the direct messaging feature, the students can send what they want through a private request to
  the instructor. This will save time and space usage for each instructor and student.

## Goals

- An Exchange feature, which will be used for students who change their sections or the course they take and can swap
  the books and material they own with others.
- The students will be able to post messages, images, and videos like they are using a social marketplace and media
  application.
- A two-factor authentication system will be implemented for the users to avoid security leaks and provide a more secure
  web application.
- For those who have difficulties checking if their sections and courses fit in the weekly schedule by hand, we plan to
  help the user with the feature to provide available sections and courses, greatly helping them and saving them even
  hours.
- A mobile application will be implemented for portability and ease of use
- Browsing in different sections like posts, listings of second-hands, donations, regular posts and announcements.

## Selling/Interesting Points

The web application will compensate for the need for a 2nd trade online platform in Bilkent. Since it is Bilkent network
only, this app will be secure, our most fundamental selling point. Further, making this platform a social place where
everyone can send their ideas anonymously further increases the uniqueness of our app.

UI will be user-friendly, attracting students to interact with each other, trade, and sell their products. Students can
make their profiles private or public while displaying their departments and hobbies in their biographies. This feature
will allow them to find friends more compatible with their interests. The app will have generic content that can provide
additional new features.

## Build Instructions

### Database

Server requires postgresql as database
To install postgresql on your local, follow the steps below:

* Install docker (If you are on windows, you should enable Windows Subsystem for Linux).
* On a terminal, run `docker volume create bilsync-sqldata`
* Change directory to `server/docker`
* Run `docker compose up -d postgresql`

These commands should run postgresql on its default port.

### Sources

We have used maven. To compile the server codes, follow the steps below:

* On the terminal, navigate to `server`
* Run `./mvnw clean install` if you are in sh or run `mvnw.cmd clean install` if you are using windows. Or just
  run `mvn clean install` if you have maven installed.
* Then, compile the code to a jar with `java -jar target/bilsync-server.jar`

We have used JDK 21 for development and test environments.

### Frontend

For the frontend ensure you are not using a headless environment, and you have npm installed,

* On the terminal, navigate to `Frontend/SynchFront`
* Run `npm install`
* Run `npm run dev`
* Go to the link provided in the console.
* From the link, you can start interacting with the frontend of the application.


## Usage
* To create a user, log in as admin, predefined in the code for now.
* Log in with that user's email and password.
* Enjoy the perfect world of Bilkent.