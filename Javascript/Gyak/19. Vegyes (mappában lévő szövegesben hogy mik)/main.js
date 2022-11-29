const form = document.querySelector('form');
const input = document.querySelector('#input');

form.addEventListener('submit', func);

const commands = ["info", "silence", "kick"];

function func(e){
    e.preventDefault();

    const inputValue = input.value;
    
    if(inputValue.charAt(0) === '!'){

        const trimmed = inputValue.substr(1);

        switch(trimmed){
            case("info"): 
                console.log('Whatever code for info');
                break;
            case("silence"): 
                console.log('Whatever code for silence');
                break;
            case("kick"): 
                 console.log('Whatever code for kick');
                break;
            default:
                console.log("Nincs ilyen");
                break;
        }

        
    }else{
        commands.forEach(com => console.log("Felkiáltó nélküli", com));
    }
}



//-------------------------------------------------------------------------------------------------


// 2 gomb együttes lenyomása esetén fut le (most enter+d) (de bármi lehetne)
// Akkor is lefut, ha ezeken kívül mást is lenyomunk, ameddig közte van az a 2 ami szükséges

/* let keysPressed = {};

document.addEventListener('keydown', (event) => {
    keysPressed[event.key] = true;

    console.log(keysPressed);
 
    if(keysPressed['Enter'] && event.key.toLowerCase() == 'd') {
        alert(event.key);
        console.log(keysPressed);
    }
 });
 
document.addEventListener('keyup', (event) => {
    delete keysPressed[event.key];
}); */





// 3 gomb együttes lenyomása esetén fut le (most ctrl+shift+k) (de bármi lehetne)
// Akkor is lefut, ha ezeken kívül mást is lenyomunk, ameddig közte van az a 3 ami szükséges

/* let keysPressed = {};

document.addEventListener('keydown', (event) => {
    keysPressed[event.key] = true;

    console.log(keysPressed);
 
    if(keysPressed['Control'] && keysPressed['Shift'] && event.key.toLowerCase() == 'k') {
        alert(event.key);
        console.log(keysPressed);
    }
 });
 
document.addEventListener('keyup', (event) => {
    delete keysPressed[event.key];
}); */





// 4 gomb együttes lenyomása esetén fut le (most enter+m+r+a) (de bármi lehetne)
// Akkor is lefut, ha ezeken kívül mást is lenyomunk, ameddig közte van az a 4 ami szükséges



/* let keysPressed = {};

document.addEventListener('keydown', (event) => {
    keysPressed[event.key.toLowerCase()] = true;

    console.log(keysPressed);
 
    if(keysPressed['enter'] && keysPressed['m'] && keysPressed['r'] && event.key.toLowerCase() == 'a') {
        alert(event.key);
        console.log(keysPressed);
    }
 });
 
document.addEventListener('keyup', (event) => {
    delete keysPressed[event.key];
});  */





// document.addEventListener('keyup', e => console.log(e.key));








let s = 'This is an apple tree';
//0-tól indexel
let a1 = s.substr(2); //2. karaktertől kezdve (3., mert 0-tól indexel) a maradékot adja vissza
let a2 = s.substr(2, 10); //2. karaktertől kezdve (3., mert 0-tól indexel), 10 hosszúságút ad vissza (erdeti 2-12-ig) (ha hosszabb, a maradékot is levágja)

let b1 = s.slice(2); //2. karaktertől kezdve (3., mert 0-tól indexel) a maradékot adja vissza
let b2 = s.slice(2, 10); //2. karaktertől kezdve (3., mert 0-tól indexel), 10 hosszúságút ad vissza (eredeti 2-10-ig) (ha hosszabb, a maradékot is levágja)

console.log('eredeti: ', s);
console.log('sub1: ', a1);
console.log('sub2: ', a2);
console.log('slice1: ', b1);
console.log('slice2: ', b2);
