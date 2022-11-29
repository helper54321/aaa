import { Directive, HostBinding, HostListener } from '@angular/core';

@Directive({
  selector: '[appDropdown]',
})
export class DropDownDirective {
  // Ha ez a directive class hozzá van adva egy html elementhez, akkor hozzáadja annak a classaihoz az opent (true esetén)
  @HostBinding('class.open') isOpen = false;

  @HostListener('click') toggleOpen() {
    this.isOpen = !this.isOpen;
  }
}
