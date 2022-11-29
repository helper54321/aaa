const item = document.querySelectorAll("#item");
const name = document.querySelectorAll("#name");
const images = document.querySelectorAll("img");
const price = document.querySelectorAll("#price");
const quantity = document.querySelectorAll("#quantity");
const cart = document.querySelectorAll("#cart");

const mainCart = document.querySelector('#main-cart');

let array = [];

/* console.log(item);
console.log(name);
console.log(images);
console.log(price);
console.log(quantity);
console.log(cart); */

cart.forEach(c => {
    c.addEventListener('click', addToCart);
});


function addToCart(e){
    e.preventDefault();
    // console.log(e.target.parentElement.previousElementSibling.children[0].children[0].innerText);
    const priceOfItem = e.target.parentElement.previousElementSibling.firstElementChild.firstElementChild.innerText;
    const quantityOfItem = e.target.parentElement.previousElementSibling.children[1].value || 1;
    const totalCostOfItem = priceOfItem * quantityOfItem;

    const imageOfItem = e.target.parentElement.previousElementSibling.parentElement.previousElementSibling;
    let trimmedOfImage = imageOfItem.src.substr(imageOfItem.src.length - 9);


    const nameOfItem = e.target.parentElement.previousElementSibling.parentElement.previousElementSibling.previousElementSibling.innerText;
    
    for(let i = 0; i < name.length; i++){
        
    }


    for(let i = 0; i < array.length; i++){
        if(array[i] == nameOfItem){
            mainCart.innerHTML += `
            <img src="img/${trimmedOfImage}" alt="">
            <h3>${nameOfItem}</h3>
            <h3>${priceOfItem} x ${quantityOfItem} = ${totalCostOfItem}</h3>
    `; 
        }
    }


    /*  if(mainCart.innerHTML.indexOf("Item 4") != -1){
        console.log('Volt már');
    } */

    mainCart.innerHTML += `
        <img src="img/${trimmedOfImage}" alt="">
        <h3>${nameOfItem}</h3>
        <h3>${priceOfItem} x ${quantityOfItem} = ${totalCostOfItem}</h3>
    `; 

    array.push(nameOfItem);
    
}