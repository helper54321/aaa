const form = document.querySelector('#form');
const searchBar = document.querySelector('#searchBar');
const minPrice = document.querySelector('#minPrice');
const maxPrice = document.querySelector('#maxPrice');
const filter = document.querySelector('input[name="filter"]:checked');
const transfer = document.querySelector('#transfer');

form.addEventListener('submit', search);

function search(e){
    e.preventDefault();

    searchBarValue = searchBar.value.toUpperCase();
    minPriceValue = +minPrice.value || 1;
    maxPriceValue = +maxPrice.value || 20;
    filterValue = filter.value;
    transferValue = +transfer.value || 20;



     if(filterValue == 'all'){
        func1();
    }else{
        filterValue = true;
        func2();
    }


    
}

function func1(){
        fetch('products.json')
        .then(res => res.json())
        .then(data => {
            let output = '<h2 class="mb-4">Users</h2>';
            data.forEach(function(product){

                let x;

                if(product.name.toUpperCase().indexOf(searchBarValue) >= 0){
                    x = true;
                }else{
                    x = false;
                }

                
                if(product.price >= minPriceValue && product.price <= maxPriceValue && product.transfer <= transferValue && x){
                output += `
                    <ul class="list-group mb-3">
                        <li class="list-group-item">Name: ${product.name}</li>
                        <li class="list-group-item">Price: ${product.price}</li>
                        <li class="list-group-item">Stock: ${product.onStock}</li>
                        <li class="list-group-item">Transfer: ${product.transfer}</li>
                    </ul>
                    <br><br>
                    `;
                }
                
            });

            document.getElementById('output').innerHTML = output;
        })
}

function func2(){
        fetch('products.json')
        .then(res => res.json())
        .then(data => {
            let output = '<h2 class="mb-4">Users</h2>';
            data.forEach(function(product){

                let x;

                if(product.name.toUpperCase().indexOf(searchBarValue) >= 0){
                    x = true;
                }else{
                    x = false;
                }

                
                if(product.price >= minPriceValue && product.price <= maxPriceValue && product.transfer <= transferValue && product.onStock == filterValue && x){
                output += `
                    <ul class="list-group mb-3">
                        <li class="list-group-item">Name: ${product.name}</li>
                        <li class="list-group-item">Price: ${product.price}</li>
                        <li class="list-group-item">Stock: ${product.onStock}</li>
                        <li class="list-group-item">Transfer: ${product.transfer}</li>
                    </ul>
                    <br><br>
                    `;
                }
                
            });

            document.getElementById('output').innerHTML = output;
        })
}