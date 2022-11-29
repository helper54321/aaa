import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'reverse'
})
export class ReversePipe implements PipeTransform {

  transform(value: any): any {
    // Megfordítja a stringet
    return value.split("").reverse().join("");
  }

}
