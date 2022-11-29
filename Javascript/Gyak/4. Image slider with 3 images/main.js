const images = document.querySelectorAll('.content div');
let active = document.querySelectorAll('.active');
let firstActive = document.querySelector('.content div.active:first-child');
let lastActive = document.querySelector('.content div.active:last-child');
const arrowLeft = document.querySelector('.left');
const arrowRight = document.querySelector('.right');

let container = document.querySelector('.container');
const shakeTime = 100; //shake transition time

images[0].innerHTML = `1 / ${images.length}`;
images[1].innerHTML = `2 / ${images.length}`;
images[2].innerHTML = `3 / ${images.length}`;

function clickLeft(){
    if(images[0] === firstActive){
        arrowLeft.style.color = 'red';
        arrowLeft.style.borderColor = 'red';

        setTimeout(() => arrowLeft.style.color = 'white', 200);
        setTimeout(() => arrowLeft.style.borderColor = 'white', 200);

        for(let i = 0; i < 2; i++){
            setTimeout(transform, shakeTime * i, ((i % 2) * 2 - 1) * 20, 0);
            setTimeout(transform, shakeTime * 2, 0, 0);
        }

        return;
    }

    for(i = 0; i < images.length; i++){
        if(images[i] == lastActive){
            images[i].classList.remove('active');
            images[i - 3].classList.add('active');
            break;
        }
    }

    images[i - 1].innerHTML = `${i} / ${images.length}`;
    images[i - 2].innerHTML = `${i - 1} / ${images.length}`;
    images[i - 3].innerHTML = `${i - 2} / ${images.length}`;

    firstActive = images[i - 3];
    lastActive = images[i - 1];
}


function clickRight(){
    if(images[images.length - 1] === lastActive){
        arrowRight.style.color = 'red';
        arrowRight.style.borderColor = 'red';

        setTimeout(() => arrowRight.style.color = 'white', 200);
        setTimeout(() => arrowRight.style.borderColor = 'white', 200);

        for(let i = 0; i < 2; i++){
            setTimeout(transform, shakeTime * i, ((i % 2) * 2 - 1) * 20, 0);
            setTimeout(transform, shakeTime * 2, 0, 0);
        }

        return;
    }
    
    for(i = 0; i < images.length; i++){
        if(images[i] == firstActive){
            images[i].classList.remove('active');
            images[i + 3].classList.add('active');
            break;
        }
    }

    images[i + 1].innerHTML = `${i + 2} / ${images.length}`;
    images[i + 2].innerHTML = `${i + 3} / ${images.length}`;
    images[i + 3].innerHTML = `${i + 4} / ${images.length}`;

    firstActive = images[i + 1];
    lastActive = images[i + 3];
}


function transform(x, y){
    container.style.transform = `translate(${x}px, ${y}px)`;
}


arrowLeft.addEventListener('click', clickLeft);
arrowRight.addEventListener('click', clickRight);