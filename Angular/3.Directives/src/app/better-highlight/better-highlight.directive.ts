import {
  Directive,
  ElementRef,
  HostBinding,
  HostListener,
  Input,
  OnInit,
  Renderer2,
} from '@angular/core';

@Directive({
  selector: '[appBetterHighlight]',
})
export class BetterHighlightDirective implements OnInit {
  //Ezeket azért hoztuk létre, mert ezek nélkül ugyan annyiban dinamikus volt, hogy hover esetén hozzáadta amúgy meg levette
  //Viszont, hogy milyen színt az hardcodeolva volt, így viszont már kintről kaphat értéket (a html-ben ahol használjuk adjuk meg)
  @Input() defaultColor: string = 'transparent';
  
  //Ebben az esetben kintről a appBetterHighlight köré nem kellett []
  // @Input() highlightColor: string = 'blue';

  //Ebben az esetben kintről [appBetterHighlight] formában használtuk, viszont azáltal egyel kevesebb propertyt kellett átadni (lényegében újrahasznosítás, mivel a directive neve is ugyanez)
  // Főleg akkor érdemes ha 1 propertyje van amit át akarunk adni, itt most a defaultcolor miatt 2
  @Input("appBetterHighlight") highlightColor: string = 'blue';

  // Korábban a lentebbi Renderer2 megoldással csináltuk, de így egyszerűbb (mind2 lehetne)
  //Itt lényegében létrehozunk egy string változót, ami a style.backgroundColor-t tudja állítani (nyilván más is lehetne)
  //Lentebb pedig a mouseenter és leave esetén ez értéket is kap. És ami értéket kap, az ténylegesen a backgroundColor lesz (mert ahhoz rendeltük hozzá)
  @HostBinding('style.backgroundColor') backgroundColor: string;

  constructor(private elRef: ElementRef, private renderer: Renderer2) {}

  //Itt mondjuk meg, hogy mi történjen azzal az elementtel amin használjuk
  //Ezzel csak annyi volt a gond, hogy statikus, tehát mindig ott lesz, ha rátesszük. Lentebb van a jobb megoldás.
  //Az itteni transparent érték csak azért kellett, hogy legyen egy kezdőértéke (ne adjon hibát, hogy nincs)
  ngOnInit(): void {
    this.backgroundColor = this.defaultColor;

    // this.renderer.setStyle(
    //   this.elRef.nativeElement,
    //   'background-color',
    //   'blue'
    // );
  }

  //Itt már dinamikusan adjuk hozzá. Megmondjuk, hogy csak mouseenter (tehát hover) esetén adja hozzá.
  @HostListener('mouseenter') mouseover(eventdata: Event) {
    // this.renderer.setStyle(
    //   this.elRef.nativeElement,
    //   'background-color',
    //   'blue'
    // );
    this.backgroundColor = this.highlightColor;
  }

  @HostListener('mouseleave') mouseleave(eventdata: Event) {
    // this.renderer.setStyle(
    //   this.elRef.nativeElement,
    //   'background-color',
    //   'transparent'
    // );
    this.backgroundColor = this.defaultColor;
  }
}
