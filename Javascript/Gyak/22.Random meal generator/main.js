const button = document.querySelector('#btn');
const food = document.querySelector('#meal');


button.addEventListener('click', getMeal);

function getMeal(){
    fetch('https://www.themealdb.com/api/json/v1/1/random.php')
        .then(resp => resp.json())
        .then(displayMeal);
}

function displayMeal(data){
    let meal = data.meals[0];
    console.log(data);

    const ingredients = [];
    const quantities = [];

    for(i = 1; i <= 20; i++){

        if(meal[`strIngredient${i}`]) {
            ingredients.push(meal[`strIngredient${i}`]);
            quantities.push(meal[`strMeasure${i}`]);
        }
    }


    let output = '<ul>';
    for(i = 0; i < ingredients.length; i++){
        output += `<li>${quantities[i]} of ${ingredients[i]}</li>`;
    }

    output += '</ul>';
    food.innerHTML = `
        <div class="grid">
            <img src="${meal.strMealThumb}" alt="">
            <div>
                <h2>${meal.strMeal}</h2>
                <div class="description">${meal.strInstructions}</div>
            </div>
        </div>
        <h2>Ingredients:</h3>
        <div class="ingredients">${output}</div>
        <iframe src="https://www.youtube.com/embed/${meal.strYoutube.slice(-11)}" ></iframe>
    `;

}