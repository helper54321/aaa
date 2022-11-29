//Callbacks

/* const posts = [
    {title: 'Post One', body: 'This is post one'},
    {title: 'Post Two', body: 'This is post two'},
];

function getPosts(){
    setTimeout(() => {
        let output = '';
        posts.forEach((post, index) => {
            output += `<li>${post.title}</li>`;
        });

        document.body.innerHTML = output;
    }, 1000); //1mp várakozási időt adtunk meg, így annyit késni fog mielőtt megjelenik
}


//Ezzel a megoldással már 2+1=3mp-ig fog tartani
function createPost(post, callback){
    setTimeout(() =>{
        posts.push(post);
        callback(); //Ennek a szükségességére az alatta lévő sorban a válasz
    }, 2000); //Mivel itt 2mp-t adtunk meg, ami több mint a másiknál, így nem fog hozzáadódni, mert mire ez befejeződik, a másik már lefutott és megjelenítette a nézetet
}

createPost({ title: 'Post Three', body: 'This is post three' }, getPosts); */




//Promises (ez a gyakoribb)

/* const posts = [
    {title: 'Post One', body: 'This is post one'},
    {title: 'Post Two', body: 'This is post two'},
];

function getPosts(){
    setTimeout(() => {
        let output = '';
        posts.forEach((post, index) => {
            output += `<li>${post.title}</li>`;
        });

        document.body.innerHTML = output;
    }, 1000); //1mp várakozási időt adtunk meg, így annyit késni fog mielőtt megjelenik
}


//Ezzel a megoldással már 2+1=3mp-ig fog tartani
function createPost(post){
    return new Promise((resolve, reject) => {
        setTimeout(() =>{
            posts.push(post);

            const error = false; //Normál esetben itt egy hibaellenőrzés lenne (lehetne)

            if(!error){
                resolve();
            }else{
                reject('Error: Something went wrong');
            }
        }, 2000); //Mivel itt 2mp-t adtunk meg, ami több mint a másiknál, így nem fog hozzáadódni, mert mire ez befejeződik, a másik már lefutott és megjelenítette a nézetet
    });
} */

//Mostmár, mivel van egy Promise amit létrehoztunk, így használhatjuk a .then-t és itt hívjuk meg a másik függvényt
/* createPost({ title: 'Post Three', body: 'This is post three' })
    .then(getPosts)
    .catch(err => console.log(err)); //Kiíratjuk a reject rész tartalmát */





//Promise.all

/* const promise1 = Promise.resolve('Hello World');
const promise2 = 10;
const promise3 = new Promise((resolve, reject) => setTimeout(resolve,2000, 'Goodbye'));

const promise4 = fetch('https://jsonplaceholder.typicode.com/users')
    .then(res => res.json()); //Itt azért van szükség a .then-re, mert fetch esetén json format-ba kell alakítani, hogy megkapjuk az adatokat


//A Promise.all esetén paraméterként egy promise-okat tartalmazó tömböt adunk meg, és arra írjuk a .then-t, így nem kell mindegyiknél egyesével
//A megjelenítéshez szükséges idő a leghosszabb promise értéke lesz
//Promise.all([promise1, promise2, promise3]).then((values) => console.log(values));

Promise.all([promise1, promise2, promise3, promise4]).then((values) => console.log(values)); */



// Async / Await

const posts = [
    {title: 'Post One', body: 'This is post one'},
    {title: 'Post Two', body: 'This is post two'},
];

function getPosts(){
    setTimeout(() => {
        let output = '';
        posts.forEach((post, index) => {
            output += `<li>${post.title}</li>`;
        });

        document.body.innerHTML = output;
    }, 1000); //1mp várakozási időt adtunk meg, így annyit késni fog mielőtt megjelenik
}


//Ezzel a megoldással már 2+1=3mp-ig fog tartani
function createPost(post){
    return new Promise((resolve, reject) => {
        setTimeout(() =>{
            posts.push(post);

            const error = false; //Normál esetben itt egy hibaellenőrzés lenne (lehetne)

            if(!error){
                resolve();
            }else{
                reject('Error: Something went wrong');
            }
        }, 2000); //Mivel itt 2mp-t adtunk meg, ami több mint a másiknál, így nem fog hozzáadódni, mert mire ez befejeződik, a másik már lefutott és megjelenítette a nézetet
    });
}



/* async function init(){
    //Megvárjuk, amíg az await jelzővel ellátott rész lefut, mielőtt továbbmennénk
    await createPost({ title: 'Post Three', body: 'This is post three' });

    getPosts();
}

init(); */



// Async / Await with fetch

async function fetchUsers(){
    const res = await fetch('https://jsonplaceholder.typicode.com/users');

    const data = await res.json();

    console.log(data);
}

fetchUsers();







