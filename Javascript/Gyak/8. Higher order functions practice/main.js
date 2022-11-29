//foreach
//map (egy részét adja vissza, pl objektumok nevét)
//filter (mindent visszaad, amire teljesül egy bizonyos feltétel)
//find (a legelsőt adja vissza, amire teljesül egy feltétel)
//every (értéke true, ha minden elemre teljesül a feltétel)
//some (értéke true, ha legalább egy elemre teljesül a feltétel)
//sort (sorba rendezés)
//reduce (egész tömbből egy értéket állít elő)


var grades = [
    {name: 'John', grade: 8, sex: 'M'},
    {name: 'Sarah', grade: 12, sex: 'F'},
    {name: 'Bob', grade: 16, sex: 'M'},
    {name: 'Johnny', grade: 2, sex: 'M'},
    {name: 'Ethan', grade: 4, sex: 'M'},
    {name: 'Paula', grade: 18, sex: 'F'},
    {name: 'Donald', grade: 5, sex: 'M'},
    {name: 'Jennifer', grade: 13, sex: 'F'},
    {name: 'Courtney', grade: 15, sex: 'F'},
    {name: 'Jane', grade: 9, sex: 'F'}
]

const ages = [33, 12, 20, 16, 5, 54, 21, 44, 61, 13, 15, 45, 25, 64, 32];


/* const sortedBySexAndGrade = grades
    .sort((a, b) => a.sex === 'M' && b.sex === 'F' ? 1 : -1)
    .sort((a, b) => a.sex === b.sex && a.grade >= b.grade ? 1 : -1)
    .map(grade => grade.name);

console.log(sorted); */


/* const maleAverageGrade = grades
    .filter(grade => grade.sex === 'M')
    .reduce((total, grade) => total + grade.grade, 0);

console.log(maleAverageGrade / grades.filter(grade => grade.sex === 'M').length); */


/* const femaleAverageGrade = grades
    .filter(grade => grade.sex === 'F')
    .reduce((total, grade) => total + grade.grade, 0);

console.log(femaleAverageGrade / grades.filter(grade => grade.sex === 'F').length); */


/* const numbers = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9];
const random = numbers
    .map(number => number = numbers[Math.floor(Math.random() * numbers.length)])
    .filter(number => number > 5)
    .sort((a, b) => a > b ? 1 : -1);

console.log(random); */


/* hasOverForty = ages.some(age => age > 40);
console.log(hasOverForty); */


/* allOverFive = ages.every(age => age > 5);
console.log(allOverFive); */


/* const agesTimesTwoHigherThanStatement = ages
    .map(age => age * 2)
    .find(age => age > 100);

console.log(agesTimesTwoHigherThanStatement); */



