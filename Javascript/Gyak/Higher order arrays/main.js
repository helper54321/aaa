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



//foreach

/* companies.forEach((company, index, companies) => {
    console.log(company);
    console.log(index);
    console.log(companies);
}); */




//filter

/* const canDrink = ages.filter(age => age >= 21);
console.log(canDrink); */


/* const retailCompanies = companies.filter(company => company.category === 'Retail');
console.log(retailCompanies); */


/* const eightiesCompanies = companies.filter(company => company.start >= 1980 && company.start < 1990);
console.log(eightiesCompanies); */


/* const lastedTenYears = companies.filter(company => company.end - company.start >= 10);
console.log(lastedTenYears); */




//map

/* const companyNames = companies.map(company => company.name);
console.log(companyNames); */


/* const test = companies.map(company => typeof company);
console.log(test); */


/* const testMap = companies.map(company => `${company.name} [${company.start} - ${company.end}]`);
console.log(testMap); */


/* const agesSquare = ages.map(age => Math.sqrt(age));
console.log(agesSquare); */


/* const agesSquareTimesTwo = ages
    .map(age => Math.sqrt(age))
    .map(age => age * 2);
console.log(agesSquareTimesTwo); */




//sort

/* const sortedCompanies = companies.sort((c1, c2) => c1.start > c2.start ? 1 : -1);
console.log(sortedCompanies); */


/* const sortAges = ages.sort((a, b) => a - b);
console.log(sortAges); */




//reduce

//A 0 a v??g??n az??rt, hogy kezdetben annyir??l induljon az ??rt??ke
/* const ageSum = ages.reduce((total, age) => total + age, 0);
console.log(ageSum); */


/* const totalYears = companies.reduce((total, company) => total + (company.end - company.start), 0);
console.log(totalYears); */




//Vegyes

const combined = ages
    .map(age => age * 2)
    .filter(age => age >= 40)
    .sort((a, b) => a - b)
    .reduce((total, age) => total + age, 0);

console.log(combined);
