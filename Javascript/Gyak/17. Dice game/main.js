const playerSelected = document.querySelector('#player-selected');
const computerSelected = document.querySelector('#computer-selected');
const pickedNumber = document.querySelectorAll('button.btn');
const playerScore = document.querySelector('#player-score');
const computerScore = document.querySelector('#computer-score');
const dice = document.querySelector('#dice');
const diceNumber = document.querySelector('#dice-number');
const turnWinner = document.querySelector('#turn-winner');

let player = 0;
let computer = 0;
let time = 1100;


pickedNumber.forEach(num => {
    num.addEventListener('click', func);
});

function func(e){

    diceNumber.innerText = '';
    turnWinner.innerText = '';

    let playerPicked = +e.target.innerText;
    let computerPicked = 1 + Math.floor(Math.random() *6);

    playerSelected.innerText = playerPicked;
    computerSelected.innerText = computerPicked;


    let generateRandom = 1 + Math.floor(Math.random() *6);
    console.log('random', generateRandom);


    for (var i = 1; i <= 1; i++){
        rollDice(i);
    }

    switch(generateRandom){
        case(1):
            setTimeout(() => diceNumber.innerText = 'It\'s a one', time);
            // dice.innerHTML = '&#xf525'
            setTimeout(() => dice.innerHTML = '&#xf525', time);
            break;
        case(2):
            setTimeout(() => diceNumber.innerText = 'It\'s a two', time);
            // dice.innerHTML = '&#xf528'
            setTimeout(() => dice.innerHTML = '&#xf528', time);
            break;
        case(3):
            setTimeout(() => diceNumber.innerText = 'It\'s a three', time);
            // dice.innerHTML = '&#xf527'
            setTimeout(() => dice.innerHTML = '&#xf527', time);
            break;
        case(4):
            setTimeout(() => diceNumber.innerText = 'It\'s a four', time);
            // dice.innerHTML = '&#xf524'
            setTimeout(() => dice.innerHTML = '&#xf524', time);
            break;
        case(5):
            setTimeout(() => diceNumber.innerText = 'It\'s a five', time);
            // dice.innerHTML = '&#xf523'
            setTimeout(() => dice.innerHTML = '&#xf523', time);
            break;
        case(6):
            setTimeout(() => diceNumber.innerText = 'It\'s a six', time);
            // dice.innerHTML = '&#xf526'
            setTimeout(() => dice.innerHTML = '&#xf526', time);
            break;
    }

    userDistance = playerPicked >= generateRandom ? playerPicked - generateRandom : generateRandom - playerPicked;

    computerDistance = computerPicked >= generateRandom ? computerPicked - generateRandom : generateRandom - computerPicked;
    console.log('userdistance', userDistance, 'computerdistance', computerDistance);

    if(userDistance < computerDistance){
        // setTimeout(() => player++, time);
        player++;
        setTimeout(() => playerScore.innerText = player, time);
        setTimeout(() => turnWinner.innerText = 'You won', time);
    }else if(userDistance > computerDistance){
        computer++;
        // setTimeout(() => computer++, time);
        setTimeout(() => computerScore.innerText = computer, time);
        setTimeout(() => turnWinner.innerText = 'Computer won', time);
    }else{
        setTimeout(() => turnWinner.innerText = 'It\'s a draw', time);
    }




    pickedNumber.forEach(num => {
        num.disabled = true;
        num.style.opacity = 0.7;

        setTimeout(() => {
            num.disabled = false;
            num.style.opacity = 1;
        }, time + 50);
    });



    /*
f525
f528
f527
f524
f523
f526
*/




    // dice.innerHTML = "&#xf525;";
    /* setTimeout(() => dice.innerHTML = '&#xf528', 60);
    setTimeout(() => dice.innerHTML = '&#xf527', 120);
    setTimeout(() => dice.innerHTML = '&#xf524', 180);
    setTimeout(() => dice.innerHTML = '&#xf523', 240);
    setTimeout(() => dice.innerHTML = '&#xf526', 300); */
};

function rollDice(i){
    setTimeout(() => dice.innerHTML = '&#xf525', 60 * i);
    setTimeout(() => dice.innerHTML = '&#xf528', 120 * i);
    setTimeout(() => dice.innerHTML = '&#xf527', 180 * i);
    setTimeout(() => dice.innerHTML = '&#xf524', 240 * i);
    setTimeout(() => dice.innerHTML = '&#xf523', 300 * i);
    setTimeout(() => dice.innerHTML = '&#xf526', 360 * i);
    setTimeout(() => dice.innerHTML = '&#xf525', 420 * i);
    setTimeout(() => dice.innerHTML = '&#xf528', 480 * i);
    setTimeout(() => dice.innerHTML = '&#xf527', 540 * i);
    setTimeout(() => dice.innerHTML = '&#xf524', 600 * i);
    setTimeout(() => dice.innerHTML = '&#xf523', 660 * i);
    setTimeout(() => dice.innerHTML = '&#xf526', 720 * i);
    setTimeout(() => dice.innerHTML = '&#xf525', 780 * i);
    setTimeout(() => dice.innerHTML = '&#xf528', 840 * i);
    setTimeout(() => dice.innerHTML = '&#xf527', 900 * i);
    setTimeout(() => dice.innerHTML = '&#xf524', 960 * i);
    setTimeout(() => dice.innerHTML = '&#xf523', 1020 * i);
    setTimeout(() => dice.innerHTML = '&#xf526', 1080 * i);
}