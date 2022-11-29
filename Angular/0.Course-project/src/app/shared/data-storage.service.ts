import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RecipeService } from '../recipes/recipe.service';
import { Recipe } from '../recipes/recipe.model';
import { map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class DataStorageService {
  constructor(private http: HttpClient, private recipeService: RecipeService) {}

  storeRecipes() {
    const recipes = this.recipeService.getRecipes();

    // Firebase esetén a put mindent felülír (az összes adatot), saját backend esetén attól függ hogy írjuk
    // Azért put, mert nem csak egyet, hanem az összeset hozzá akarjuk adni
    this.http
      .put(
        'https://ng-course-recipe-book-bb883-default-rtdb.firebaseio.com/recipes.json',
        recipes
      )
      .subscribe((response) => {
        console.log(response);
      });
  }

  fetchRecipes() {
    return this.http
      .get<Recipe[]>(
        'https://ng-course-recipe-book-bb883-default-rtdb.firebaseio.com/recipes.json'
      )
    //   Itt a pipe mapje az rxjx operator, a return-nél pedig higher array function
      .pipe(map(recipes => {
        return recipes.map(recipe => {
            // Mivel létrehozhatunk úgy is receptet, hogy nem adunk meg ingredientet, így legalább üres array-ként tárolja, hogy rá való hivatkozásnál ne legyen hiba
            return {...recipe, ingredients: recipe.ingredients ? recipe.ingredients : []}
        })
      }),
      tap(recipes => {
          this.recipeService.setRecipes(recipes);
      })
      )
  }
}
