const numberInput = document.querySelector('#numberInput');
let select = document.querySelector('#select');
let form = document.querySelector('#form');
let eredmeny = document.querySelectorAll('.eredmeny');
let res = document.querySelector('#result');

/* fetch('https://api.exchangeratesapi.io/latest')
    .then(res => res.json())
    .then(data => console.log(data)); */

const values = {
    huf: 0.0031,
    eur: 1.09,
    usd: 1,
    gbp: 1.26,
    aud: 0.64,
    chf: 1.04
}


form.addEventListener('submit', getValues);


function getValues(e){

    e.preventDefault();

    let numberInputValue = numberInput.value;
    let selectValue = select.value;
    let outputValue;

    if(numberInputValue < 1 || selectValue === 'null'){
        let container = document.querySelector('.container');
        let info = document.querySelector('.info');


        let p = document.createElement('p');
        p.innerText = 'Please fill in all fields';
        p.classList.add('error');
        container.insertBefore(p, info);

        setTimeout(() => p.style.display = 'none', 3000);

        return;
    }

    var x = Object.keys(values);
    var x2= Object.keys(values);
    var y = Object.values(values);

    let z;


    for(i = 0; i < x.length; i++){
        if(x[i] == selectValue){
            z = y[i];
            x.splice(i, 1);
            y.splice(i, 1);
        }
    }

    let resDiv = document.querySelectorAll('.result div');

    for(i = 0; i < resDiv.length; i++){
        if(resDiv.length > 4){
            resDiv[i].remove();
        }
    }


    for(i = 0; i < x.length; i++){

        let result = document.querySelector('#result');

        let div = document.createElement('div');
        let h2 = document.createElement('h2');
        let h3 = document.createElement('h3');

        

        h2.innerText = x[i].toUpperCase();

        h3.classList.add('eredmeny');

        outputValue = (numberInputValue * z) / values[x[i]]

        outputValue = Math.floor(outputValue * 100) / 100;
        outputValue.toFixed(2);

        h3.innerText = outputValue;

        
        div.appendChild(h3);
        div.appendChild(h2);

        result.appendChild(div);
    }
}