# 📚 ReadMe.com 📚
## 🤔 Buy and sell web store with user book reviews.

Web application made for the Web Applications Developement course from Universidad Rey Juan Carlos.

## 🤷‍♂️ Team organization

### Team members

| Name                        | Email                              |
| --------------------------- | ---------------------------------- |
| Gonzalo Ortega Carpintero   | g.ortega.2019@alumnos.urjc.es      | 
| Sergio Hernández Sandoval   | s.hernandezsa.2019@alumnos.urjc.es | 
| Markos Aguirre Elorza       | m.aguirre.2016@alumnos.urjc.es     |
| Manuel Martin Alaez         | m.martinal.2017@alumnos.urjc.es    | 
| Alberto Jiménez Gómez       | a.jimenezg.2017@alumnos.urjc.es    | 

### Trello board
To organice the team tasks we will be using the following [Trello board](https://trello.com/invite/b/AfoK9mBL/ATTI96f3e1b8aaf8c3c26c3bd2a450f9f137B4F49542/tareas).


## 🌟 Main web features
<details><summary> Features that our page will have once the develpoment is complete. </summary>

Entities:
 - Users.
 - Books.
 - Book reviews.
 - Book offers.
 - Buy and sell records.

Types of users:
- Unregistered - Can see book ofers a read book reviews.
- Registered - Can write reviews, publish book offers and buy books.
- Administrator - Can add new books and manage offers and accounts.

User permits:
- Unregistered - No data collection.
- Registered - Writen and read reviews, seen and published offers, bought and sold items and user profile with profile image, email and username.
- Administrator - Total access, no saved data.

Images:
- User profile image.
- Book image.
- Ofers images.

Charts:
- Published books and offers statistics.

Complementary technology:

- Email delivery to new users.
- Advance search and recomendations algorithim based on read reviews and seen offers.
</details>
    
    
## 🗺️ Front-end and structure design

<details><summary> Main pages of our application. </summary>

<details><summary>Home page</summary>
    
Application main page where recomended products are displayed in accordance with the user preferences.

![Home page (index)](https://user-images.githubusercontent.com/66415975/219658565-1e529d67-d4b0-4750-b02e-e9e9eb8eef33.png)
</details>
    
<details><summary>Books general page</summary>
    
Page to display the searched books and filter them by genre.

![Books general page](https://user-images.githubusercontent.com/66415975/219658750-f5242d27-b335-4df2-b1c8-49cdd696c72d.png)
</details>
    
<details><summary>Book particular page</summary>
    
Page to display the reviews and offers of a particular book.

![Book particular page](https://user-images.githubusercontent.com/66415975/219658767-411a93a2-9e74-44f5-83d3-5ebcd8a581c4.png)
</details>
    
<details><summary>Upload review page</summary>
    
Page to write a review of a book so everyone can read it.

![Upload review page](https://user-images.githubusercontent.com/66415975/219658789-e0b9b0ef-86a1-404d-a68a-b5e2e310b2ab.png)
</details>
    
<details><summary>Upload offer page</summary>
    
Page to publish an offer of a book you want to sell.

![Upload offer page](https://user-images.githubusercontent.com/66415975/219658794-45496920-9ef2-40fd-8a9c-fb202cf517ae.png)
</details>
    
<details><summary>Offer page</summary>
    
Page to buy a book offer fro an other user.

![Offer page](https://user-images.githubusercontent.com/66415975/219658805-b1d731fd-366d-4aca-b804-2044cadd5a9f.png)
</details>
    
<details><summary>Checkout page</summary>
    
Page to introduce shoping details and complete an order.

![Checkout page](https://user-images.githubusercontent.com/66415975/219658822-51988111-da9b-4062-997b-0e7bdff83de1.png)
</details>
    
<details><summary>Contact page</summary>
    
Page with info about us.

![Contact page](https://user-images.githubusercontent.com/66415975/219658836-a140c949-ba62-4fea-a105-29af432d89f1.png)
</details>
    
<details><summary>Statistics page</summary>
    
Page with stats about from the application and books.

![Statistics page](https://user-images.githubusercontent.com/66415975/219658859-2a0de304-5ca5-4b8e-81cf-905dd39de660.png)
</details>
    
<details><summary>Login page</summary>
    
Page to introduce your user credentials.

![Login page](https://user-images.githubusercontent.com/66415975/219658872-cb26fe3f-1e20-4cc0-a249-3e28877bc419.png)
</details>
    
<details><summary>Register page</summary>
    
Page to make an account into aour application.

![Register page](https://user-images.githubusercontent.com/66415975/219658891-33f79de5-9980-4c01-829d-5e351cc456d9.png)
</details>
    
<details><summary>User page</summary>
    
Page to display the users information, including its account information, its uploaded offers, its shoping record and its favourites books.

![User page](https://user-images.githubusercontent.com/66415975/219659186-1db25ff0-e457-4b0f-b540-bf7b992603a7.png)
</details>
    
<details><summary>Admin page</summary>
    
Page to upload new books to the database and manage all the application data.
</details>
</details>

<details><summary>Pages diagram.</summary>
![Pages diagram drawio](https://user-images.githubusercontent.com/66415975/219658345-556ecc2a-fcca-4785-b695-70ebc885aaf9.png)
</details>
    


## 🚀 PHASE 2

### 📱 Screens

<details><summary>Updated screens.</summary>

</details>

<details><summary>Screen navigation diagram.</summary>

</details>


### 🛠 Technology description and development run instructions
- Java version: 17
- SpringBoot version: 2.4.4
- Database: MySQL
   - Scheme: `books`
   - User: `root`
   - Password: `password`

To start the application run the `ReadmeBookstoreApplication.java` and go to `https://localhost:8443/` on your browser.


## 📊 Diagrams

<details><summary>Database entity diagram.</summary>
   
   Diagram in which the different entities in the database are related.
   
![Database entities diagram](https://user-images.githubusercontent.com/80122593/223448947-4ba30519-b7fa-48e7-8114-8e7b7f37c408.png)

</details>

<details><summary>Java class diagram.</summary>
   
   Diagram in which the different java classes are related.
   
![Java class diagram](https://user-images.githubusercontent.com/80122593/223449581-fdffcbea-90c6-43d9-ab10-16498201dda4.jpg)


</details>

<details><summary>Java class diagram and templates.</summary>
   
   Diagram in which all the java classes of the application and the templates are related.
   
![Diagram of classes and templates](https://user-images.githubusercontent.com/80122593/223449590-5b0e14ba-deba-4596-a68b-6a8959a63b33.jpg)


</details>


## 🙋‍♂️ Member participation

<details><summary>Gonzalo Ortega Carpintero.</summary>
 
   - 📂 Completed tasks:
 
      - Spring project initialization.
      - Admin page HTML and funcionalities, including visualizing, editing and deleting, all data from models.
      - Upload books and its images as an admin funcionality.
      - Upload and buy offers funtionalities.
      - Statistics page with dinamic bar diagram.
      - General style and dessign changes.
 
   - 📤 Most significant commits:
 
      - [Commit 1](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/9df20a0ad8345938ae5cc57aee1c55c778aa50fb) Spring project initialized.
      - [Commit 2](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/c122b0668aee6804e66840218e9caf11a35bc2bb) Upload books and edit data from admin page.
      - [Commit 3](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/9868bedb6e66ff8913c62c4255b35136b445d045) Upload offers functionality.
      - [Commit 4](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/bc9959d2367621bfe4242b0b1abdd674cda9191d) Statistic page.
      - [Commit 5](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/6edd943e35a1c769a25ac8bdbbabf5cc42fc6976) Admin funtionalities complete.
 
   - 📝 Files with more participation:
 
      - [File 1](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/resources/templates/admin-page.html) admin-page.html
      - [File 2](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/controller/AdminController.java) AdminController.java
      - [File 3](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/controller/OfferController.java) OfferController.java
      - [File 4](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/controller/StatisticsController.java) StatisticsController.java
      - [File 5](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/resources/static/js/statistics.js) statistics.js

</details>

<details><summary>Sergio Hernández Sandoval.</summary>
   
   - 📂 Completed tasks:
 
      - Header and footer unification for templates.
      - Model, Service and Repository of reviews, including improvements and changes in the rest of the models.
      - Review controller and high participation in user, book and offer controller.
      - Initialization of part of the data in the databaseInitializer.
      - Implemented the funcionality of show/modify/delete the offers not sold from a user in his profile.
      - Implemented the funcionality of show/modify/delete the reviews from a user in his profile.
      - Implemented the funcionality of show/delete the favorite books from a user in his profile.
      - Implemented the funcionality of show the buy and sell historial from a user in his profile.
      - Some searchs with querys in repositorys.
      - Show the books in book general page.
      - Show the offers and reviews in particular book page.
      - Participation in the pagination.
      - Upload offers and upload reviews in a book.
      - Delete or modify reviews in the profile.
      - Modify the image of an offer.
      - Pages of error, including the controller and the template.
      - Changes in the style of the screens.
      - Improvements and bug fixes.
      - Drawing diagrams for documentation.
      
 
   - 📤 Most significant commits:
 
      - [Commit 1](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/f67665f9359539683d14647b3647a062bef61a80) Upload the classes related to reviews.
      - [Commit 2](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/cf0a8a6574aa549515d62e9ad24789a1ce960b9b) Show books and the offers and reviews of a book.
      - [Commit 3](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/3c993095deb14e63eea3fb2e4cca244eaffae7ad) Upload offers.
      - [Commit 4](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/6761d884f74ff745fc9e0da5b1611ff2113782b3) Upload reviews.
      - [Commit 5](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/57d13b1eaedcec21c55242bd424481c13d366a37#diff-ae449e0b1f8ad774bb28b01895ca5dc5c4e6c722ea5706b49acdcc9405656b4f) User profile.
 
   - 📝 Files with more participation:
 
      - [File 1](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/controller/BookController.java) BookController.java
      - [File 2](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/controller/OfferController.java) OfferController.java
      - [File 3](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/controller/ReviewController.java) ReviewController.java
      - [File 4](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/controller/UserController.java) UserController.java
      - [File 5](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/resources/templates/user-page.html) User-page.html

</details>

<details><summary>Markos Aguirre Elorza .</summary>
   
      
   - 📂 Completed tasks:
 
      - Elemental funtionalities of the User entityModel, Service and  Repository
      - Login
      - Register (uploading/updating text and image for the first time to the server)
      - User information display in the user-page (in collaboration with Sergio)
      - Everything regarding to security
      - Email sending additional technology implementation
      
 
   - 📤 Most significant commits:
 
      - [Commit 1] https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/d75173db7d2b6464450b46bf088d08b95e69c4e2) Login and Register
      - [Commit 2] https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/b5babf57ad5c516280f3071ea84a273eb3abe32a
      - [Commit 3] https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/f068e7238f96c670206f7aba0676584c075ab25c
      - [Commit 4] https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/f2283ca3e59e9a8a66499c7f5e1564dd9a796fa9
      - [Commit 5] https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/724fb0bb8aad783aafde0bce6c89f3d3d6beb7e5
     
 
   - 📝 Files with more participation:
 
      - [File 1] https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/resources/templates/user-page.html
      - [File 2] https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/security/SecurityConfiguration.java
      - [File 3] https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/service/MailService.java
      - [File 4] https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/controller/LoginController.java
      - [File 5] https://github.com/CodeURJC-DAW-2022-23/webapp6/blame/main/backend/src/main/java/es/codeurjc/readmebookstore/model/User.java
     

</details>

<details><summary>Manuel Martin Alaez.</summary>
   
   - 📂 Completed tasks:
 
      - a
      - b
      - c
 
   - 📤 Most significant commits:
 
      - [Commit 1]()
      - [Commit 2]()
      - [Commit 3]()
      - [Commit 4]()
      - [Commit 5]()
 
   - 📝 Files with more participation:
 
      - [File 1]()
      - [File 2]()
      - [File 3]()
      - [File 4]()
      - [File 5]()

</details>

<details><summary> Alberto Jiménez Gómez.</summary>
   
   - 📂 Completed tasks:
 
      - a
      - b
      - c
 
   - 📤 Most significant commits:
 
      - [Commit 1]()
      - [Commit 2]()
      - [Commit 3]()
      - [Commit 4]()
      - [Commit 5]()
 
   - 📝 Files with more participation:
 
      - [File 1]()
      - [File 2]()
      - [File 3]()
      - [File 4]()
      - [File 5]()

</details>

