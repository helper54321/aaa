/* const s1 = 'Hello';
console.log(typeof s1); //Stringet ad vissza

const s2 = new String('Hello');
console.log(typeof s2); //Objectet ad vissza */

//console.log(navigator.appVersion); //Információt ad az operációs rendszerrel amivel használjuk


//object literal (haszontalan)

/* const book1 = {
    title: 'Book One',
    author: 'John Doe',
    year: '2013',
    getSummary: function(){
        return `${this.title} was written by ${this.author} in ${this.year}`;
    }
};


const book2 = {
    title: 'Book Two',
    author: 'Jane Doe',
    year: '2016',
    getSummary: function(){
        return `${this.title} was written by ${this.author} in ${this.year}`;
    }
};

console.log(book1.getSummary());
console.log(book2.getSummary());

console.log(Object.values(book2)); //Megadja a változók értékeit, és a metódusokat
console.log(Object.keys(book2)); //Megadja a változók neveit, és a metódusok nevét

console.log(typeof book1); */



//Konstruktor

/* function Book(title, author, year){
    this.title = title;
    this.author = author;
    this.year = year;

    // Nem jó megoldás konstruktorban létrehozni, mert így a változók melletti jelenik meg kiíratásnál, és ezt nem akarjuk
    this.getSummary = function(){
        return `${this.title} was written by ${this.author} in ${this.year}`;
    }
}

const book1 = new Book('Book One', 'John Doe', '2013');
const book2 = new Book('Book Two', 'Jane Doe', '2016');

console.log(book1.title);

console.log(book1.getSummary()); */



//Prototypes

/* function Book(title, author, year){
    this.title = title;
    this.author = author;
    this.year = year;
}

//getSummary
//A Book (ebben az esetben) annak a típusnak a neve, aminek a prototypejához hozzá akarjuk adni
Book.prototype.getSummary = function(){
    return `${this.title} was written by ${this.author} in ${this.year}`;
};

//getAge
Book.prototype.getAge = function(){
    const years = new Date().getFullYear() - this.year;
    return `${this.title} is ${years} years old`;
};

//Revise (change year)
Book.prototype.revise = function(newYear){
    this.year = newYear;
    this.revised = true;
};

const book1 = new Book('Book One', 'John Doe', '2013');
const book2 = new Book('Book Two', 'Jane Doe', '2016');

console.log(book2);

console.log(book2.getAge());

book2.revise('2018');

console.log(book2); */



//Inheritance

/* function Book(title, author, year){
    this.title = title;
    this.author = author;
    this.year = year;
}

Book.prototype.getSummary = function(){
    return `${this.title} was written by ${this.author} in ${this.year}`;
};


//Magazine constructor
function Magazine(title, author, year, month){
    Book.call(this, title, author, year); //Meghívjuk a szülő konstruktorát

    this.month = month;
}

//Instatiate magazin object
//const mag1 = new Magazine('Mag One', 'John Doe', '2018', 'Jan');

//console.log(mag1.getSummary()); //Hibát dobna, mert a metódust nem örököltettük

//Inherit prototype
Magazine.prototype = Object.create(Book.prototype);

const mag1 = new Magazine('Mag One', 'John Doe', '2018', 'Jan');

console.log(mag1.getSummary()); //Így már nem hibás, mert örököltettük

//Use magazine constructor

Magazine.prototype.constructor = Magazine; //Enélkül a Book-nak a konstruktorát használná (kiíratásnál a proto alatt meg is tudjuk nézni)

console.log(mag1); */



//Object Of Protos (ritka megoldás, talán felesleges is)

/* const bookProtos = {
    getSummary: function(){
        return `${this.title} was written by ${this.author} in ${this.year}`;        
    },
    getAge: function(){
        const years = new Date().getFullYear() - this.year;
        return `${this.title} is ${years} years old`;
    }
}

//Create Object

//const book1 = Object.create(bookProtos);
//book1.title = 'Book One';
//book1.author = 'John Doe';
//book1.year = '2013'; 

const book1 = Object.create(bookProtos, {
    title : { value: 'Book One'},
    author : { value: 'John Doe'},
    year : { value: '2013'},
});

console.log(book1); */



//Classes

/* class Book{
    constructor(title, author, year){
        this.title = title;
        this.author = author;
        this.year = year;
    }

    getSummary(){
        return `${this.title} was written by ${this.author} in ${this.year}`;
    }

    getAge(){
        const years = new Date().getFullYear() - this.year;
        return `${this.title} is ${years} years old`;
    }

    revise(newYear){
      this.year = newYear;
      this.revised = true;  
    }

    static topBookStore(){
        return 'Barnes & Noble';
    }
}

const book1 = new Book('Book One', 'John Doe', '2013');

console.log(book1.getSummary());
console.log(book1.getAge());
book1.revise('2018');
console.log(book1); 

//book1.topBookStore(); //Hiba, mert statikus metódust nem példányosítva kell használni

console.log(Book.topBookStore()); */



//Subclasses

class Book{
    constructor(title, author, year){
        this.title = title;
        this.author = author;
        this.year = year;
    }

    getSummary(){
        return `${this.title} was written by ${this.author} in ${this.year}`;
    }
}

//Magazine subclass

class Magazine extends Book{
    constructor(title, author, year, month){
        super(title, author, year);
        this.month = month;
    }
}

const mag1 = new Magazine('Mag One', 'John Doe', '2018', 'Jan');

console.log(mag1.getSummary());
