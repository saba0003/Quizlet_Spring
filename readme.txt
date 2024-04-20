In order to start the application go to the root directory, find .exe file and run it.

Below are the endpoints for application testing:

Endpoints: {

   // Flashcards
   http://localhost:8080/api/v1/flashcards
   http://localhost:8080/api/v1/flashcards/{flashcardId}
   http://localhost:8080/api/v1/flashcards/{flashcardId}/edit
   http://localhost:8080/api/v1/flashcards/{flashcardId}/delete
   http://localhost:8080/api/v1/flashcards/new

   // Questions
   http://localhost:8080/api/v1/questions
   http://localhost:8080/api/v1/questions/{questionId}
   http://localhost:8080/api/v1/questions/{questionId}/edit
   http://localhost:8080/api/v1/questions/{questionId}/delete
   http://localhost:8080/api/v1/questions/new
   http://localhost:8080/api/v1/questions/quiz
}

'.../v1/flashcards' and '.../v1/questions' - lists existing flashcards and questions representatively.
'.../model/{modelId}', '.../model/{modelId}/edit', '.../model/{modelId}/delete' and '.../model/{modelId}/new' - 
these are CRUD operations on according models (Flashcard/Question).
'/questions/quiz' - simple quiz game containing questions from db.

The way ID generation strategy works is that from the initial ID=1 it goes onward by ones for each new representative entry in the database.
So, for example, if you want to see the details of the third question you can write '.../question/3' in the url.

It's also worth noting that the way the database is configured, it is created upon starting the application with some entries for template
purposes and dropped upon finish, meaning that data is lost after terminating the application.

There is no explicit way to stop the application, but this can be done by opening task manager, type javaw in the search bar and end task for the background process. 
there can be multiple background processes but our application can be identified with the most memory usage.
