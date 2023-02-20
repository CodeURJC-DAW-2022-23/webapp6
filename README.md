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

### Technology description and development run instructions
- Java version: 17
- SpringBoot version: 3.0.2
- Database: MySQL
   - Scheme: `books`
   - User: `root`
   - Password: `password`

To start the application run the `ReadmeBookstoreApplication.java` and go to `https://localhost:8443/` on your browser.

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
    
## 🏛️ Back-end design

<details><summary>Database entities diagram.</summary>
![Database entities diagram](https://user-images.githubusercontent.com/66415975/220188452-d654cd89-2a4a-4287-9366-38f570ef6d8d.png)
</details>



