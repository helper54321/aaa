import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  Resolve,
  RouterStateSnapshot,
} from '@angular/router';
import { Observable } from 'rxjs';
import { DataStorageService } from '../shared/data-storage.service';
import { Recipe } from './recipe.model';
import { RecipeService } from './recipe.service';

@Injectable({ providedIn: 'root' })
export class RecipesResolverService implements Resolve<Recipe[]> {
  constructor(
    private dataStorageService: DataStorageService,
    private recipesService: RecipeService
  ) {}

  // Ennek szerepe, hogy ha pl a módosító oldalon vagyunk, akkor alapból még nincsenek betöltve a receptek, így ha pl ott frissítjük az oldalt akkor hibát ad
  // Ennek a resolver-nek a lefutásával viszont a route betöltése előtt alapból lefut ez a kód, így már meglesznek az adatok (ehhez az app-routing.module-ban a megfelelő helyeken kellett a resolve rész)
  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Recipe[] | Observable<Recipe[]> | Promise<Recipe[]> {
    const recipes = this.recipesService.getRecipes();

    if (recipes.length === 0) {
      return this.dataStorageService.fetchRecipes();
    } else {
      // Ha már be vannak töltve, ne töltsük újra mert felülírja az előzőt, akkor is ha pl módosítottunk közben
      return recipes;
    }
  }
}
