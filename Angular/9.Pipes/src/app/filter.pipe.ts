import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filter',
  // Itt a pure magyarázata a 17. mappa 249-es videójában van, nem nehéz és rövid videó, inkább csak magyarázat
  // Kb annyit csinál, hogy amikor szűrünk valamire (pl stable status, akkor van pl 5 találat), de amikor hozzáadunk egy újat, akkor nem fut le újra a pipehoz tartozó kód, mert nagyon energiaigényes is lehetne
  // Ha mi mégis azt akarjuk, hogy tegye, akkor itt megadhatjuk a pure részt (ami opcionális, default true, így olyankor nem is muszáj megadni)
  // Tehát érthetően, true esetén akkor fut le, ha a paraméter értéke változik (a : utáni rész a html-ben), míg false esetén bármilyen html oldalon lévő változás esetén
  pure: false
})
export class FilterPipe implements PipeTransform {
  transform(value: any, filterString: string, propName: string): any {
    if (value.length === 0 || filterString === "") {
      return value;
    }

    const resultArray = [];
    for (const item of value) {

      // Itt az item egy objektum, a propName pedig annak egy propertyje, tehát a lentebbi lehet pl item.status === filterString
      // De így ebben a formában megadva bármilyen paraméterként kapottként használható, míg az item.propName esetén azt hinné hogy a propName maga a property
      if (item[propName] === filterString) {
        resultArray.push(item);
      }
    }

    return resultArray;
  }
}
