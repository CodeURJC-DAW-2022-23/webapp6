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
    
## 🚀 PHASE 1
<details><summary>Front-end and structure design</summary>

### 🗺️ Main Pages

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
Pages diagram:

![Pages diagram drawio](https://user-images.githubusercontent.com/66415975/219658345-556ecc2a-fcca-4785-b695-70ebc885aaf9.png)
</details>

</details>
    


## 🚀 PHASE 2
<details><summary>Backend developement with Spring, Java and MySQL</summary>

### 📱 Screens

<details><summary>Updated screens.</summary>
Screenshots of the updated interface:
 
![inicio](https://user-images.githubusercontent.com/49288214/224589396-da27f2a9-ca4b-431c-bf34-88d070eda88a.png)
 
![libros](https://user-images.githubusercontent.com/49288214/224589416-648fe8cd-35b8-455a-bf01-17541f0f9eed.png)
 
![contacto](https://user-images.githubusercontent.com/49288214/224589422-f72feee6-8560-455b-9746-f879e074964b.png)

![estadisticas](https://user-images.githubusercontent.com/49288214/224589429-b2c0b17c-1118-41b3-9f46-5d254f6a1e91.png)
 
![inicio_sesion](https://user-images.githubusercontent.com/49288214/224589500-ef8d300f-119d-454f-9d02-dfc1708397ce.png)

![registro](https://user-images.githubusercontent.com/49288214/224589515-b2d3196c-32cb-4d6d-aaa4-e2864d907522.png)

![user-page](https://user-images.githubusercontent.com/49288214/224589552-cd04e3bb-2853-43f8-927b-df86a6eec8a0.png)
![user-page2](https://user-images.githubusercontent.com/49288214/224589566-7f455628-4328-4ef4-ab16-9c9089ab7618.png)
![user-page3](https://user-images.githubusercontent.com/49288214/224589575-f72b6a88-2bb2-43f7-beaf-00ad87a48f0e.png)
 
![modificar_usuario](https://user-images.githubusercontent.com/49288214/224589618-bf580ef5-7122-435d-ae86-1e2c415a76ef.png)


![admin1](https://user-images.githubusercontent.com/49288214/224589585-992c30f2-ec37-4aef-a504-2c74ebfd5f69.png)
![admin2](https://user-images.githubusercontent.com/49288214/224589590-f16f66ed-1b87-462d-9b80-b8287ed57538.png)
![admin3 (2)](https://user-images.githubusercontent.com/49288214/224590246-7e260a02-ebd0-4e89-a5a6-b5da29eb2096.png)


 ![libro](https://user-images.githubusercontent.com/49288214/224589604-96960197-1c31-4862-bd84-cee37d8c57b0.png)

 ![publicar reseña](https://user-images.githubusercontent.com/49288214/224589654-c99f94b1-4bcd-45ee-8a6a-a6fbf755cfb6.png)

 ![vender libro](https://user-images.githubusercontent.com/49288214/224589660-e3f85dff-7145-41dc-b71d-8a3dcd50c9f6.png)

 ![reseña](https://user-images.githubusercontent.com/49288214/224589671-3bfb00ef-e0bd-4238-a0f9-4e9f1deea203.png)

 ![comprar](https://user-images.githubusercontent.com/49288214/224589684-6c046f52-79b7-4bd1-9416-ef01c4388d51.png)

</details>

<details><summary>Screen navigation diagram.</summary>
Updated navegation diagram:

![diagrama navegacion](https://user-images.githubusercontent.com/49288214/224589368-d600796a-c852-4b06-a6b2-955b29822902.jpg)
</details>


### 🛠 Technology description and development run instructions
- Java version: 17
- SpringBoot version: 2.4.4
- Database: MySQL
   - Scheme: `readmewebstore`
   - User: `root`
   - Password: `password`

To start the application run the `ReadmeBookstoreApplication.java` and go to `https://localhost:8443/` on your browser.


## 📊 Diagrams

<details><summary>Database entity diagram.</summary>
   
   Diagram in which the different entities in the database are related.
   
![Database entities diagram](https://user-images.githubusercontent.com/80122593/223448947-4ba30519-b7fa-48e7-8114-8e7b7f37c408.png)

</details>

<details><summary>Java class diagram of the models.</summary>
   
   Diagram in which the different java classes are related.
   
![Java class diagram](https://user-images.githubusercontent.com/80122593/223449581-fdffcbea-90c6-43d9-ab10-16498201dda4.jpg)


</details>

<details><summary>Java class diagram of controllers.</summary>
   
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
 
      - [Commit 1](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/d75173db7d2b6464450b46bf088d08b95e69c4e2) Login and Register
      - [Commit 2](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/b5babf57ad5c516280f3071ea84a273eb3abe32a)
      - [Commit 3](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/f068e7238f96c670206f7aba0676584c075ab25c)
      - [Commit 4](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/f2283ca3e59e9a8a66499c7f5e1564dd9a796fa9)
      - [Commit 5](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/724fb0bb8aad783aafde0bce6c89f3d3d6beb7e5)
     
 
   - 📝 Files with more participation:
 
      - [File 1](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/resources/templates/user-page.html)
      - [File 2](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/security/SecurityConfiguration.java)
      - [File 3](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/service/MailService.java)
      - [File 4](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/controller/LoginController.java)
      - [File 5](https://github.com/CodeURJC-DAW-2022-23/webapp6/blame/main/backend/src/main/java/es/codeurjc/readmebookstore/model/User.java)
     

</details>

<details><summary>Manuel Martin Alaez.</summary>
   
   - 📂 Completed tasks:
 
      - Load images from database
      - Load more button in books
      - Load more button for partial search
      - Load more buttons for admin page and user page
      - Load more buttons for offers and reviews
      - Bugs and improvements
      - Navegation diagram
 
   - 📤 Most significant commits:
 
      - [Commit 1](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/8f9731eb2479b627a5ca76e3d8d61c27543983ca) Images
      - [Commit 2](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/990d2f620c862dfe9c3bd3751c9bb0ace85a4001) Load more
      - [Commit 3](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/6866f4d9ce69808df59c6be7e995d57032638918) Load more user
      - [Commit 4](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/eeae7ca68632e579528c3bb92debf049905ba690) Load more search functional
      - [Commit 5](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/234c66adf197acb8a9a12fc2482f6259884f7619) Admin load more and bug corrections
 
   - 📝 Files with more participation:
 
      - [File 1](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/resources/static/js/load.js)
      - [File 2](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/controller/BookController.java)
      - [File 3](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/model/Offer.java)
      - [File 4](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/resources/templates/admin-page.html)
      - [File 5](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/service/OfferService.java)

</details>

<details><summary> Alberto Jiménez Gómez.</summary>
   
   - 📂 Completed tasks:
 
      - Database initialization. 
      - Data of books, categories, favorite books, and bought books.
      - Searchtool, it is possible to search a book looking for the title, or a list of books looking for author, genre or a partial part of those properties.
      - Dropdown categories by genre using search controller.
      - Add or remove a book to favorites from the particular book page.
      - Algorithm of recomendation made in static.
      - Categories entity used in the recommendation algorithm.
      - Update of algorithm to dynamic using the database data.
 
   - 📤 Most significant commits:
 
      - [Commit 1](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/80d9669ae6ffa1fa6b651a263b9ed3a49a7dab49) Initialize database
      - [Commit 2](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/c5d387c1d845a7f997e3719ae7eab1dd11bdb9c0) Search books
      - [Commit 3](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/827767a00ee39158f53914db24dad8a9d9619048) Add books to favorites
      - [Commit 4](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/56430068d85ffca01fa039fe533546ffb6ae866e) Static Search Algorithm
      - [Commit 5](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/948c0683288322c1cfbc72173285d8d72f5653eb) Dynamic Search Algorithm
 
   - 📝 Files with more participation:
 
      - [File 1](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/controller/AlgorithmController.java) AlgorithmController.java
      - [File 2](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/controller/BookController.java) BookController.java
      - [File 3](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/repository/BookRepository.java) BookRepository.java
      - [File 4](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/controller/UserController.java) UserController.java
      - [File 5](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/resources/templates/book-particular-page.html) book-particular-page.html

</details>
</details>

## 🚀 PHASE 3
<details><summary>API rest integration and Docker deployment</summary>

### ✒ API Rest documentation

<details><summary>Open API specification.</summary>

 - [Link to yaml file](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/api-docs/api-docs.yaml)
 
 - [Link to html file](https://rawcdn.githack.com/CodeURJC-DAW-2022-23/webapp6/34cef72e0849b41e0397cc2d5f623f591249c2d6/backend/api-docs/api-docs.html)
 
</details>

### ✒ Docker instructions

<details><summary>Docker image creation</summary>

 To create a Docker image of our proyect, download the complete proget and run: \
 `docker build -t readmebookstore/webapp6 -f docker/Dockerfile .`
 
</details>

<details><summary>Docker compose run structions</summary>

 To run our application container directly form Docker Hub theéonly requirements are to have Docker installed and a copy of our 
 [Doker Compose file](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/docker/docker_compose.yml).
 
 Then, just run the following command indicating the Docker Compose file location: \
 `docker-compose -f docker/docker_compose.yml up`
 
</details>

## 📊 Diagrams

<details><summary>Java class diagram of web and rest controllers.</summary>
   
   Updated diagram in which the java classes of the application and the templates are related.
   
![diagrama](https://user-images.githubusercontent.com/80122593/227806030-8cacd206-a192-4852-8a1c-11372e8ce6bc.png)

</details>

## 🙋‍♂️ Member participation

<details><summary>Gonzalo Ortega Carpintero.</summary>
 
   - 📂 Completed tasks:
 
      - Web URLs reformat to facilizate the API implementation.
      - Book related APIs.
      - Doker files added (althogh not working).
 
   - 📤 Most significant commits:
 
      - [Commit 1](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/e92b1328c09aa2f053023c72468f37caa1c177ad) URL reformat. 
      - [Commit 2](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/7c497dbef610485dc11b4789e4420426722d655a) Book GET operations.
      - [Commit 3](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/040e0ef71e99e332028946058a7f9b9aefc827ec) Book POST, PUT and DELETE operations.
      - [Commit 4](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/a240ba87e104c3a66fa0c04bc223e30d39624342) Books images POSTS and DELETES.
      - [Commit 5](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/1eef5cddb418e632855a185e5be75daf1a62c67b) Docker directory files.
 
   - 📝 Files with more participation:
 
      - [File 1](backend/src/main/java/es/codeurjc/readmebookstore/controller/rest/BookRestController.java) BookRestContoller.java
      - [File 2](backend/src/main/java/es/codeurjc/readmebookstore/controller/rest/AdminRestController.java) AdminRestController.java
      - [File 3](backend/src/main/java/es/codeurjc/readmebookstore/controller/LoginController.java) LoginController.java
      - [File 4](docker/Dockerfile) Dockerfile
      - [File 5](docker/docker_compose.yml) docker_compose.yml

</details>

<details><summary>Sergio Hernández Sandoval.</summary>
   
   - 📂 Completed tasks:
 
      - APIs related to reviews by user.
      - APIs related to reviews by admin.
      - APIs related to user by session user.
      - APIs related to user by admin.
      - APIs related to show statistics.
      - Passing duplicate code used in web and rest from controllers to services.
      - Improvements and bug fixes.
      - Updated class diagram.
      - Readme structuring.
      
 
   - 📤 Most significant commits:
 
      - [Commit 1](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/843d5a76c659a2e63dcd1c4c95a83ec27fc31ba0) ReviewRestController initialized.
      - [Commit 2](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/55d1c99930edfb1fadacad80a1706bf9a33582d4) UserRestController finished.
      - [Commit 3](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/7963c381376baa346f8a559555061ce2909b17b1) StatisticsRestController started and completed.
      - [Commit 4](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/fbd13385042709c03d79f567b16becf190b36e8d) Added the postman petition collection.
      - [Commit 5](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/83097e82911bb03d185fddc5bcf3b19801ef504c) Added documentation of APIs.
 
   - 📝 Files with more participation:
 
      - [File 1](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/controller/rest/AdminRestController.java) AdminRestController
      - [File 2](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/controller/rest/ReviewRestController.java) ReviewRestController
      - [File 3](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/controller/rest/StatisticsRestController.java) StatisticsRestController
      - [File 4](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/controller/rest/UserRestController.java) UserRestController
      - [File 5](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/model/Review.java) Review.java

</details>

<details><summary>Markos Aguirre Elorza.</summary>
   
   - 📂 Completed tasks:
 
      - Login API
      - Register API (email sending)
      - Security
      - Access permissions
 
   - 📤 Most significant commits:
 
      - [Commit 1](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/63f6dbfc638b383d176008a2a4e06607aa3f3ae5) Security.
      - [Commit 2](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/03aee5cefc1b012bbd4c91d828b0045bcfd2cc11) Register.
      - [Commit 3](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/ab59f5783e9a44920b10f550e17c3ca03b84ae2b) Relevant DTO pattern.
      - [Commit 4](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/aae7bf9d261ed9f7b5168d0fd948a8cb804cf841) Email sending.
      - [Commit 5](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/535e99fbbf2a02cb81e7aa1161af193002ebd420) Permissions.
 
   - 📝 Files with more participation:
 
      - [File 1](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/controller/rest/AuthRestController.java) AuthRestController
      - [File 2](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/model/UserDTO.java) UserDTO
      - [File 3](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/security/SecurityConfiguration.java) SecurityConfiguration
      - [File 4](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/security/RestSecurityConfig.java) RestSecurityConfig.java
      - [File 5](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/security/jwt/UserLoginService.java) UserLoginService
     

</details>

<details><summary>Manuel Martin Alaez.</summary>
   
   - 📂 Completed tasks:
 
      - APIs related to offers.
      - APIs related to offers by admin.
      - OpenApi documentation.
      - Bugs fixes.
 
   - 📤 Most significant commits:
 
      - [Commit 1](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/91199caacf53714904567906f73d8f146237b52e) 
      - [Commit 2](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/7a44bce0b6ca5c817deb79e3b145e93013ca5649) 
      - [Commit 3](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/f421a964154a20b68fe20097c243bb3ee268b02b)
      - [Commit 4](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/dbdd6d7f19e18bb7442eb81f03bf3e8b1b3154b9) 
      - [Commit 5](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/d75cdde413996be41b21736c8401d00378d63ac1) 
 
   - 📝 Files with more participation:
 
      - [File 1](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/controller/rest/OfferRestController.java) OfferRestController.java
      - [File 2](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/controller/rest/AdminRestController.java) AdminRestController.java
      - [File 3](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/pom.xml) pom.xml
      - [File 4](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/api-docs/api-docs.html) api-docs.html
      - [File 5](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/api-docs/api-docs.yaml) api-docs.yaml

</details>

<details><summary> Alberto Jiménez Gómez.</summary>
   
   - 📂 Completed tasks:
 
      - AlgorithmService.
      - Insert search functions in BookService.
      - Api for pageable searched books and find all books.
      - Api for Algorithm.
      - Api to add favorite books.
      - Modify api to remove favorite books.
      - Move principal algorithm function to AlgorithmService.
      - Removing Algorithm web and rest controller files.
      - Modify book web and rest controller to launch the algorithm directly.
      - Remove duplicated and unused code.
 
   - 📤 Most significant commits:
 
      - [Commit 1](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/c4637a5fe677ffb0418a5b5437261e6ef5f4e8f5) Change Algorithm functions to a Service.
      - [Commit 2](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/da0e3a5aa643025b92048cfe1b3aa5eada653e8a) Change Searcg functions to a Service.
      - [Commit 3](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/10f5439189db1cca740d22e53622cce333e5fd2f) Api for pageable searched books and findall books.
      - [Commit 4](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/653a71aff7467f942eafc50d06b9b605f3337904) Functional algorithm api.
      - [Commit 5](https://github.com/CodeURJC-DAW-2022-23/webapp6/commit/e33767696955417ac18da22b80d45515425a00db) Api for add favorites and remove duplicated code in algorithm api.
 
   - 📝 Files with more participation:
 
      - [File 1](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/controller/rest/BookRestController.java) BookRestController.java
      - [File 2](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/controller/rest/UserRestController.java) UserRestController.java
      - [File 3](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/service/AlgorithmService.java) AlgorithmService.java
      - [File 4](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/service/BookService.java) BookService.java
      - [File 5](https://github.com/CodeURJC-DAW-2022-23/webapp6/blob/main/backend/src/main/java/es/codeurjc/readmebookstore/controller/web/BookController.java) BookController.java

</details>
</details>


## 🚀 PHASE 4
<details><summary>Web application as SPA client with Angular and deployment</summary>

### ✒ Development environment
 
 <details><summary>Instructions for running the SPA application with Angular.</summary>

 
</details>


## 📊 Diagrams

<details><summary>SPA diagram.</summary>
   
   SPA class diagram and templates.
   
![SPAdiagram](https://user-images.githubusercontent.com/80122593/233835761-21318e10-f0d9-40a1-b727-a2fecb8f9cf8.png)


</details>

## 🙋‍♂️ Member participation

<details><summary>Gonzalo Ortega Carpintero.</summary>
 
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

<details><summary>Sergio Hernández Sandoval.</summary>
   
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

<details><summary>Markos Aguirre Elorza.</summary>
   
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
</details>
