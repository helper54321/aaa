const form = document.querySelector('#form');
const input = document.querySelector('#input');
const submit = document.querySelector('#submit');
const output = document.querySelector('.output');
const previousGuesses = document.querySelector('#previous-guesses');
const lives = document.querySelector('#lives');
const highLow = document.querySelector('#high-low');
const max = document.querySelector('#max-random');

let initialLives = 10;
let numbers = [];
const maxRandom = 500;

max.innerHTML += `Guess a number between 1 and ${maxRandom}`;

let random = generate();
form.addEventListener('submit', func);


function generate(){
    const random = 1 + Math.floor(Math.random() * maxRandom);
    return random;
}


function func(e){
    e.preventDefault();
    highLow.style.display = 'block';

    for(let i = 0; i < numbers.length; i++){
        if(numbers[i] == input.value){
            input.value = '';
            highLow.innerHTML = 'You already guessed that number';
            setTimeout(() => highLow.style.display = 'none', 2000);
            return;
        }
    }

    if(input.value < 1 || input.value > maxRandom){
        input.value = '';
        highLow.innerHTML = `Number must be between 1 and ${maxRandom}`;
        setTimeout(() => highLow.style.display = 'none', 2000);
        return;
    }

    const value = input.value;
    numbers.push(value);

    input.value = '';

    if(random != value){
        initialLives--;
        if(initialLives == 0){
            highLow.innerHTML = `Sorry. You lost.`;
            input.disabled = true;
            submit.disabled = true;
            submit.cursor = 'default';

            setTimeout(() => highLow.style.display = 'none', 2000);

            const again = document.createElement('a');
            again.classList.add('.btn');
            again.id = 'again';
            again.appendChild(document.createTextNode('Play again'));
            setTimeout(() => output.insertBefore(again, null), 2000);

            const againButton = document.querySelector('#again');

            again.addEventListener('click', playAgain);
        }
        lives.innerHTML = `Remaining lives: ${initialLives}`;
        previousGuesses.innerHTML += `${value} `;
        console.log(previousGuesses.innerHTML);
    }

    if(random < value){
        if(initialLives > 0){
            highLow.innerHTML = `Number is too high`;
        }
    }else if(random > value){
        if(initialLives > 0){
            highLow.innerHTML = `Number is too low`;
        }
    }else{
        highLow.innerHTML = `That's the number. Congratulations.`;
        input.disabled = true;
        submit.disabled = true;
        submit.cursor = 'default';

        setTimeout(() => highLow.style.display = 'none', 2000);

        const again = document.createElement('a');
        again.classList.add('.btn');
        again.id = 'again';
        again.appendChild(document.createTextNode('Play again'));
        setTimeout(() => output.insertBefore(again, null), 2000);

        const againButton = document.querySelector('#again');

        again.addEventListener('click', playAgain);
    }
}


function playAgain(){
    again.remove();
    input.value = '';
    numbers = [];
    input.disabled = false;
    submit.disabled = false;
    initialLives = 10;
    lives.innerHTML = `Remaining lives: ${initialLives}`;
    previousGuesses.innerHTML = 'Previous guesses: ';
    random = generate();
}