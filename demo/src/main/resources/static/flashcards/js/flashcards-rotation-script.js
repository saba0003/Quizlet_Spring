// Select all elements with the class "card"
const cards = document.querySelectorAll('.card');

// Script for card rotation
cards.forEach(card => {
    // Add event listener for click
    card.addEventListener('click', () => {
        // Toggle styles on click
        if (card.style.transform === 'rotateY(180deg)') {
            // If already rotated, revert back to original state
            card.style.transform = 'rotateY(0deg)';
        } else {
            // Otherwise, rotate the card
            card.style.transform = 'rotateY(180deg)';
        }
    });
});
