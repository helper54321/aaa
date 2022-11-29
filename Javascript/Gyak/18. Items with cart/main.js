const item = document.querySelectorAll("#item");
const name = document.querySelectorAll("#name");
const images = document.querySelectorAll("img");
const price = document.querySelectorAll("#price");
const quantity = document.querySelectorAll("#quantity");
const cart = document.querySelectorAll("#cart");

const showCart = document.querySelector('#show-cart');
const mainCart = document.querySelector('#main-cart-items');
const totalItemNumbers = document.querySelector('#total-item-numbers');
const totalItemCost = document.querySelector('#total-item-cost');

let totalCostOfAllItems = 0;
let totalQuantityOfAllItems = 0;


cart.forEach(c => {
    c.addEventListener('click', addToCart);
});

showCart.addEventListener('click', showCartContent);


function addToCart(e){
    e.preventDefault();
    const priceOfItem = e.target.parentElement.previousElementSibling.firstElementChild.firstElementChild.innerText;
    let quantityOfItem = e.target.parentElement.previousElementSibling.children[1].value || 1;
    quantityOfItem > 10 ? quantityOfItem = 10 : quantityOfItem = quantityOfItem;

    totalQuantityOfAllItems += +quantityOfItem;
    
    const totalCostOfItem = priceOfItem * quantityOfItem;
    totalCostOfAllItems += +totalCostOfItem;

    if(totalQuantityOfAllItems > 0){
        showCart.innerHTML = `<i class="fas fa-shopping-cart"></i><span id="total-item-numbers">${totalQuantityOfAllItems}</span> Items - $<span id="total-item-cost">${totalCostOfAllItems.toFixed(2)}</span>`;
    }else{
        showCart.innerHTML = ` <i class="fas fa-shopping-cart"></i><span id="total-item-numbers">Empty cart</span> `;
        mainCart.style.display = 'none';
    }


    const imageOfItem = e.target.parentElement.previousElementSibling.parentElement.previousElementSibling;
    let trimmedOfImage = imageOfItem.src.substr(imageOfItem.src.length - 9);


    const nameOfItem = e.target.parentElement.previousElementSibling.parentElement.previousElementSibling.previousElementSibling.innerText;

    mainCart.innerHTML += `
        <div id="it">
            <img src="img/${trimmedOfImage}" alt="">
            <h3>${nameOfItem}</h3>
            <h3>${priceOfItem} x ${quantityOfItem} = ${totalCostOfItem.toFixed(2)}</h3>
            <a href="#"><i class="fas fa-trash" id="remove"></i></a>
        </div>
    `; 


    document.querySelectorAll('#remove').forEach(rem => {
        rem.addEventListener('click', removeFromCart);
    });
    
}

function showCartContent(e){
    e.preventDefault();

    if(mainCart.style.display == 'none' && totalQuantityOfAllItems > 0){
        mainCart.style.display = 'grid';
    }else{
        mainCart.style.display = 'none';
    }
}


function removeFromCart(e){
    const reduce = e.target.parentElement.previousElementSibling.innerText;
    const equalMarkForSum = reduce.indexOf("=");
    let reducedSum = reduce.substr(equalMarkForSum + 2);
    totalCostOfAllItems -= reducedSum;

    const xMark = reduce.indexOf("x");
    let reducedBeforeXMark = reduce.substr(xMark + 2);
    const equalMarkForQuantity = reducedBeforeXMark.indexOf("=");
    let reducedQuantity = reducedBeforeXMark.slice(0, equalMarkForQuantity);
    totalQuantityOfAllItems -= reducedQuantity;

    if(totalQuantityOfAllItems > 0){
        showCart.innerHTML = `<i class="fas fa-shopping-cart"></i><span id="total-item-numbers">${totalQuantityOfAllItems}</span> Items - $<span id="total-item-cost">${totalCostOfAllItems.toFixed(2)}</span>`;
    }else{
        showCart.innerHTML = ` <i class="fas fa-shopping-cart"></i><span id="total-item-numbers">Empty cart</span> `;
        mainCart.style.display = 'none';
    }

    e.target.parentElement.parentElement.remove();
}

