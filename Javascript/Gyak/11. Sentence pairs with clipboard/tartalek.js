const container = document.querySelector('.container');



const sentences = [
    {
        front: 'Nem találtuk meg Tamást.',
        back: 'We didn\'t find Tom.'
    },
    {
        front: 'Fogalmam sincs, Tom hogyan törte el azt az üveget.',
        back: 'I have no idea how Tom broke that glass.'
    },
    {
        front: 'Új autóm van.',
        back: 'I have a new car.'
    },
    {
        front: 'Mardos a lelkiismeret.',
        back: 'I have a guilty consciousness.'
    },
    {
        front: 'Veszíteni fogtok.',
        back: 'You will lose.'
    },
    {
        front: 'Sokkoló volt, amit láttam..',
        back: 'What I saw was shocking.'
    },
    {
        front: 'Azt gondolom, hogy mindnyájan menjünk el Tomékhoz.',
        back: 'I think we should all go to Tom\'s house.'
    },
    {
        front: 'Mit csinálunk vacsorára?',
        back: 'What would you like for supper?'
    },
    {
        front: 'Lépéseket hallottam.',
        back: 'I heard footsteps.'
    },
    {
        front: 'Nekem van otthon egy barátnőm.',
        back: 'I have a girlfriend back home.'
    },
]

let array = [];

for(let i = 0; i < 5; i++){
    const random = Math.floor((Math.random() * sentences.length));
    array.push(sentences[random].front);

    container.innerHTML += `
        <div class="content">
            <div>
                <h2>${sentences[random].front}</h2>
                <button class="btn" id="clipboard" title="Copy">
                    <i class="fas fa-clipboard fa-2x"></i>
                </button>
            </div>

            <hr>

            <div>
            <h2>${sentences[random].back}</h2>
                <button class="btn" id="clipboard" title="Copy">
                    <i class="fas fa-clipboard fa-2x"></i>
                </button>
            </div>
        </div>
        `;
}

const clipboard = document.querySelectorAll('i');

for(let i = 0; i < clipboard.length; i++){
    clipboard[i].addEventListener('click', e => {
    const textarea = document.createElement('textarea');
    const copyText = e.target.parentElement.previousElementSibling.innerHTML;

    textarea.value = copyText;
    document.body.appendChild(textarea);
    textarea.select();
    document.execCommand('copy');

    textarea.remove(); //Itt már nincs rá szükség, ezért törölhető
    });
}