import { Directive, ElementRef, OnInit } from '@angular/core';

// Ahhoz hogy végül használni tudjuk, az app.module.ts-ben a declarationsnál meg kellett adni

@Directive({
  selector: '[appBasicHighlight]',
})
export class BasicHighlightDirective implements OnInit {
  //Hogy megtudjuk melyik elemnél használjuk (mint pl hol használjuk a gyári ngIf-et, tehát melyik html element, ahhoz hasonló)
  //Működik, de jobb módja is van. A better-hightlight.directive.ts-ben úgy csináljuk
  constructor(private elementRef: ElementRef) {}

  ngOnInit(): void {
    this.elementRef.nativeElement.style.backgroundColor = 'green';
  }
}
