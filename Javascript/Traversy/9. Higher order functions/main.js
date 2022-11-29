const companies= [
    {name: "Company One", category: "Finance", start: 1981, end: 2004},
    {name: "Company Two", category: "Retail", start: 1992, end: 2008},
    {name: "Company Three", category: "Auto", start: 1999, end: 2007},
    {name: "Company Four", category: "Retail", start: 1989, end: 2010},
    {name: "Company Five", category: "Technology", start: 2009, end: 2014},
    {name: "Company Six", category: "Finance", start: 1987, end: 2010},
    {name: "Company Seven", category: "Auto", start: 1986, end: 1996},
    {name: "Company Eight", category: "Technology", start: 2011, end: 2016},
    {name: "Company Nine", category: "Retail", start: 1981, end: 1989}
  ];
  
  const ages = [33, 12, 20, 16, 5, 54, 21, 44, 61, 13, 15, 45, 25, 64, 32];


//for és forEach

/* for(let i = 0; i < companies.length; i++){
    console.log(companies[i]);
} */

//Lehetséges paraméterek
// company: Aktuális elem
// index: Az aktuális elem indexe
//companies: Maga az egész tömb
//companies.forEach(function (company, index, companies)


/* companies.forEach(function(company){
    console.log(company.name);
}); */



//for és filter

/* let canDrink = [];
for(let i = 0; i < ages.length; i++){
    if(ages[i] >= 21){
        canDrink.push(ages[i]);
    }
}

console.log(canDrink); */


/* const canDrink = ages.filter(function(age){
    if(age >= 21){
        return true;
    }
}); */


/* const canDrink = ages.filter(age => age >= 21);

console.log(canDrink); */


//filter 2. példa

/* const retailCompanies = companies.filter(function(company){
    if(company.category === 'Retail'){
        return true;
    }
});

console.log(retailCompanies); */


/* const retailCompanies = companies.filter(company => company.category === 'Retail');

console.log(retailCompanies); */


//filter 3. példa

/* const eightiesCompanies = companies.filter(company => company.start >= 1980 && company.start <1990);

console.log(eightiesCompanies); */

//filter 4. példa

/* const lastedTenYears = companies.filter(company => company.end - company.start >= 10);

console.log(lastedTenYears); */



//map

/* const companyNames = companies.map(function(company){
    return company.name;
}); */

/* const companyNames = companies.map(function(company){
    return  `${company.name} [${company.start} - ${company.end}}]`;
}); */

/* const companyNames = companies.map(company => `${company.name} [${company.start} - ${company.end}}]`);

console.log(companyNames); */

/* const agesSquare = ages.map(age => Math.sqrt(age));

console.log(agesSquare); */


/* const agesTimesTwo = ages.map(age => age * 2);

console.log(agesTimesTwo); */

/* const ageMap = ages
    .map(age => Math.sqrt(age))
    .map(age => age * 2);

console.log(agesSquare); */



//sort

//Start date szerinti növekvő sorrendbe rendezés
/* const sortedComapnies = companies.sort(function(c1, c2){
    if(c1.start > c2.start){
        return 1;
    }else{
        return -1;
    }
});

console.log(sortedCompanies); */

//Start date szerinti csökkenő sorrendbe rendezés
/* const sortedCompanies = companies.sort(function(c1, c2){
    if(c1.start > c2.start){
        return -1;
    }else{
        return 1;
    }
});

console.log(sortedCompanies); */


/* const sortedCompanies = companies.sort((a, b) => (a.start > b.start ? 1 : -1));

console.log(sortedCompanies); */


// Csökkenő sorrendhez a végén b-a
/* const sortAges = ages.sort((a, b) => a - b);

console.log(sortAges); */



//for és reduce

/* let ageSum = 0;
for(let i = 0; i < ages.length; i++){
    ageSum += ages[i];
}

console.log(ageSum); */


/* const ageSum2 = ages.reduce(function(total, age){ // A total lesz az amibe az összeget számoljuk
    return total + age;
},0); // a total kezdeti értéke

console.log(ageSum2); */


/* const ageSum2 = ages.reduce((total, age) => total + age, 0);

console.log(ageSum2); */


// get total years for all companies
/* const totalYears = companies.reduce((total,company) => total + (company.end - company.start), 0);

console.log(totalYears); */



//Vegyített példa

const combined = ages
    .map(age => age * 2)
    .filter(age => age >= 40)
    .sort((a, b) => a-b)
    .reduce((total, age) => total + age, 0);

console.log(combined);
