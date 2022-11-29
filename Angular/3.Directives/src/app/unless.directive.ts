import { Directive, Input, TemplateRef, ViewContainerRef } from '@angular/core';

// Ez a directive a beépített ngIf ellenkezőjét szolgálja (tehát akkor jeleníti meg ha hamis)

@Directive({
  selector: '[appUnless]',
})
export class UnlessDirective {
  @Input() set appUnless(condition: boolean) {
    if (!condition) {
      // A vcRef visszaadja hogy hová akarunk renderelni, a templateRef pedig hogy mit, így itt átadható
      this.vcRef.createEmbeddedView(this.templateRef);
    } else {
      //Ne jelenítse meg (mert feltételtől függően akarjuk csak)
      this.vcRef.clear();
    }
  }

  // Mit akarunk renderelni és hová a 2 paraméter
  //Tehát  az 1. paraméterrel hozzáférünk a <ng-template></ng-template> részhez
  constructor(
    private templateRef: TemplateRef<any>,
    private vcRef: ViewContainerRef
  ) {}
}
