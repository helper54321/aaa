import { Pipe, PipeTransform } from '@angular/core';

// Ez egy általunk létrehozott pipe (a transformban pedig azt adjuk vissza vele, amivé változtatni akarjuk az inputot)
// Ahhoz, hogy működjön az app.module-ban a declarationsnál meg kell adni!!!!!!
// A @Pipe name részénél megadott néven tudjuk használni
@Pipe({
  name: 'shorten',
})
export class ShortenPipe implements PipeTransform {
  transform(value: any, limit: number) {
    if(value.length > limit){
        return value.substr(0, limit) + " ...";
    }

    return value;
  }
}
