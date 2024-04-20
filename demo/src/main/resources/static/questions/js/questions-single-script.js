const question = parseQuestionFromDb();

const questionElement = document.getElementById("question");
const answerButtons = document.getElementById("answer-buttons");

function showQuestion() {
    // Clear existing answer buttons
    answerButtons.innerHTML = '';

    // Display question description
    questionElement.innerHTML = question.description;

    // Display answer buttons
    question.answers.forEach(answer => {
        const button = document.createElement("button");
        button.innerHTML = answer.text;
        button.classList.add("btn");
        answerButtons.appendChild(button);

        if (answer.correct) {
            button.dataset.correct = answer.correct;
            button.classList.add("correct");
        } else {
            button.classList.add("incorrect");
        }
    });
}

function parseQuestionFromDb() {
    // Select question from database
    const questionFromDb = document.querySelector('.question');

    // Parsing Data
    const description = questionFromDb.querySelector('.description').innerText;
    const answerElements = questionFromDb.querySelectorAll('.possible-answer');

    const answers = [];

    answerElements.forEach(answerElement => {
        const answerText = answerElement.innerText;
        const isCorrect = answerElement.classList.contains('correct-answer');

        answers.push({text: answerText, correct: isCorrect});
    });

    return {
        description: description,
        answers: answers
    };
}

// Add event listeners to answer buttons to disable them on hover
answerButtons.addEventListener('mouseover', function(event) {
    if (event.target.classList.contains('btn')) {
        event.target.disabled = true;
        event.target.style.pointerEvents = 'none';
    }
});

showQuestion();
