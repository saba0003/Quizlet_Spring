// Template
const questions = [
//    {
//        description: "Which is the largest animal on the planet?",
//        answers: [
//            {text: "Shark", correct: false},
//            {text: "Blue whale", correct: true},
//            {text: "Elephant", correct: false},
//            {text: "Giraffe", correct: false}
//        ]
//    },
//    {
//        description: "Which is the smallest country on the planet?",
//        answers: [
//            {text: "Vatican City", correct: true},
//            {text: "Bhutan", correct: false},
//            {text: "Nepal", correct: false},
//            {text: "Shri Lanka", correct: false}
//        ]
//    },
//    {
//        description: "Which is the largest desert on the planet?",
//        answers: [
//            {text: "Kalahari", correct: false},
//            {text: "Gobi", correct: false},
//            {text: "Sahara", correct: false},
//            {text: "Antarctica", correct: true}
//        ]
//    },
//    {
//        description: "Which is the smallest continent on the planet?",
//        answers: [
//            {text: "Asia", correct: false},
//            {text: "Australia", correct: true},
//            {text: "Arctic", correct: false},
//            {text: "Africa", correct: false}
//        ]
//    }
];

const questionElement = document.getElementById("question");
const answerButtons = document.getElementById("answer-buttons");
const nextButton = document.getElementById("next-btn");

let currentQuestionIndex = 0;
let score = 0;

function startQuiz() {
    addQuestionsFromDb();
    currentQuestionIndex = 0;
    score = 0;
    nextButton.innerHTML = "Next";
    showQuestion();
}

function showQuestion() {
    resetState();

    let currentQuestion = questions[currentQuestionIndex];
    let questionNo = currentQuestionIndex + 1;
    questionElement.innerHTML = questionNo + ". " + currentQuestion.description;

    currentQuestion.answers.forEach(answer => {
        const button = document.createElement("button");
        button.innerHTML = answer.text;
        button.classList.add("btn");
        answerButtons.appendChild(button);
        if (answer.correct)
            button.dataset.correct = answer.correct;
        button.addEventListener("click", selectAnswer);
    });
}

function showScore() {
    resetState();
    questionElement.innerHTML = `you scored ${score} out of ${questions.length}!`;
    nextButton.innerHTML = "Play Again";
    nextButton.style.display = "block";
}

function resetState() {
    nextButton.style.display = "none";

    while(answerButtons.firstChild)
        answerButtons.removeChild(answerButtons.firstChild);
}

function selectAnswer(e) {
    const selectedBtn = e.target;
    const isCorrect = selectedBtn.dataset.correct === "true";

    if (isCorrect) {
        selectedBtn.classList.add("correct");
        score++;
    } else {
        selectedBtn.classList.add("incorrect");
    }

    Array.from(answerButtons.children).forEach(button => {
        if (button.dataset.correct === "true")
            button.classList.add("correct");
        button.disabled = true;
    });

    nextButton.style.display = "block";
}

function handleNextButton() {
    currentQuestionIndex++;
    if (currentQuestionIndex < questions.length)
        showQuestion();
    else
        showScore();
}

// Function to hide all questions except the first one
function addQuestionsFromDb() {
    const questionsFromDb = document.querySelectorAll('.question');

    for (let i = 1; i < questionsFromDb.length; i++)
        questionsFromDb[i].style.display = 'none';

    const parsedQuestions = [];

    questionsFromDb.forEach(question => {
        const parsedQuestion = parseDataFromHtml(question);
        parsedQuestions.push(parsedQuestion);
    });

    // Push parsed questions to the global 'questions' array
    questions.push(...parsedQuestions);
}

// Function to parse data from HTML
function parseDataFromHtml(questionFromDb) {
    const description = questionFromDb.querySelector('.description').innerText;
    const possibleAnswers = questionFromDb.querySelectorAll('.possible-answer');

    const answers = [];

    possibleAnswers.forEach(possibleAnswer => {
        const answerText = possibleAnswer.innerText;
        const isCorrect = possibleAnswer.classList.contains('correct-answer');
        answers.push({text: answerText, correct: isCorrect});
    });

    return {
        description: description,
        answers: answers
    };
}

nextButton.addEventListener("click", () => {
    if (currentQuestionIndex < questions.length)
        handleNextButton();
    else
        startQuiz();
});

startQuiz();
