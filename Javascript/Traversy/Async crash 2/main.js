/* const posts = [
    {title: 'Post One', body: 'This is post one'},
    {title: 'Post Two', body: 'This is post two'}
];

function getPosts(){
    setTimeout(() => {
        let output = '';
        posts.forEach(post => {
            output += `<li>${post.title}</li>`;
        });

        document.body.innerHTML = output;
    }, 1000);
}


function createPost(post){
    setTimeout(() => {
        posts.push(post);
    }, 2000);
}

getPosts();

createPost({title: 'Post Three', body: 'This is post three'}); */



//callbacks


/* const posts = [
    {title: 'Post One', body: 'This is post one'},
    {title: 'Post Two', body: 'This is post two'}
];

function getPosts(){
    setTimeout(() => {
        let output = '';
        posts.forEach(post => {
            output += `<li>${post.title}</li>`;
        });

        document.body.innerHTML = output;
    }, 1000);
}


function createPost(post, callback){
    setTimeout(() => {
        posts.push(post);
        callback();
    }, 2000);
}

createPost({title: 'Post Three', body: 'This is post three'}, getPosts); */


//promises


/* const posts = [
    {title: 'Post One', body: 'This is post one'},
    {title: 'Post Two', body: 'This is post two'}
];

function getPosts(){
    setTimeout(() => {
        let output = '';
        posts.forEach(post => {
            output += `<li>${post.title}</li>`;
        });

        document.body.innerHTML = output;
    }, 1000);
}


function createPost(post){
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            posts.push(post);

            const error = false;

            if(!error){
                resolve();
            }else{
                reject('Error: Something went wrong');
            }
            
        }, 2000);
    });
}

createPost({ title: 'Post Three', body: 'This is post three'})
    .then(getPosts)
    .catch(err => console.log(err)); //Lekezeljük a promise reject esetét (ezt csinálja ha hiba van, most így kezeljük le) */



//promise.all

/* const promise1 = Promise.resolve('Hello World');
const promise2 = 10;
const promise3 = new Promise((resolve, reject) => {
    setTimeout(resolve, 2000, 'Goodbye');
});
const promise4 = fetch('https://jsonplaceholder.typicode.com/users').then(res => res.json());
//Itt a legutolsó kivételesnek számít, mert ugyan ez is promise-t return-öl, de ennél 2db .then-t szokás használni
//Működne 1-el is, de nem olyan formában adná vissza ahogy akarjuk, így ezt először formázni szoktuk, és utána adjuk vissza az adatot (ezért van a 2db .then)


Promise.all([promise1, promise2, promise3, promise4])
    .then(values => console.log(values)); */



//async & await

const posts = [
    {title: 'Post One', body: 'This is post one'},
    {title: 'Post Two', body: 'This is post two'}
];

function getPosts(){
    setTimeout(() => {
        let output = '';
        posts.forEach(post => {
            output += `<li>${post.title}</li>`;
        });

        document.body.innerHTML = output;
    }, 1000);
}


function createPost(post){
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            posts.push(post);

            const error = false;

            if(!error){
                resolve();
            }else{
                reject('Error: Something went wrong');
            }
            
        }, 2000);
    });
}

/* async function init(){
    //Az await hatására megvárjuk amíg ez befejeződik, addig nem megyünk tovább a getPost-ra
    await createPost({ title: 'Post Three', body: 'This is post three'});

    getPosts();
}

init(); */


// Async & Await with fetch

async function fetchUsers(){
    const res = await fetch('https://jsonplaceholder.typicode.com/users');

    const data = await res.json();

    console.log(data);
}

fetchUsers();

