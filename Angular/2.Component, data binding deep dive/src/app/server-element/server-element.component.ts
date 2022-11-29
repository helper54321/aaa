import {
  AfterContentChecked,
  AfterContentInit,
  AfterViewChecked,
  AfterViewInit,
  Component,
  DoCheck,
  Input,
  OnChanges,
  OnDestroy,
  OnInit,
  SimpleChanges,
  ViewChild,
  ContentChild,
  ViewEncapsulation,
  ElementRef,
} from '@angular/core';

@Component({
  selector: 'app-server-element',
  templateUrl: './server-element.component.html',
  styleUrls: ['./server-element.component.css'],

  // Ha olyan stílust akarok létrehozni ebben a classben, ami nem csak erre a componentre vonatkozzon
  // Pl ha ennek a componentnek a css-ben p{} között megadok stílust akkor alapból csak ennek a componentnek a p-jeire vonatkozik. Viszont ha a lentebbi sort megadom, akkor az összes component p-jére
  //Valószínűleg nem nagyon akarjuk használni, de lehetséges
  // encapsulation: ViewEncapsulation.None
})
export class ServerElementComponent
  implements
    OnInit,
    OnChanges,
    DoCheck,
    AfterContentInit,
    AfterContentChecked,
    AfterViewInit,
    AfterViewChecked,
    OnDestroy
{
  // Itt a @Input jelentése, hogy ne csak ennek a componentnek a html-je férhessen hozzá ehhez a propertyhez, hanem a parent componentek is (tehát amiben használjuk ezt a componentet)
  //  @Input() element: { type: string; name: string; content: string };

  // Itt egy aliast adunk neki, hogy más néven használhassuk a parentben, csak az egyértelműség miatt (természetesen nem lenne kötelező)
  // Viszont innentől már csak az alias néven használható
  // @Input('srvElement') element: { type: string; name: string; content: string };
  @Input() name: string;

  //Cockipitben is használjuk, ott magyarázom
  @ViewChild('heading', { static: true }) header: ElementRef;

  // Hasonló a viewchild-hoz, a különbség, hogy a viewchild a saját html kódban lévő referenciához fér hozzá
  // Míg a contentchild ahhoz amit lényegében props.children közötti részben referenciálunk, pl: app-ben átadjuk a servernek: <server><p #referencia></p></server>
  @ContentChild('contentParagraph', { static: true }) paragraph: ElementRef;

  constructor() {
    console.log('Constructor called');
  }

  // Először a konstruktor és ngOnInit között, majd azután csak ha változás történt (useEffect szerű?)
  ngOnChanges(changes: SimpleChanges): void {
    console.log('ngOnChanges called');
  }

  // Konstruktor után
  ngOnInit(): void {
    console.log('ngOninit called');

    //Itt még nincs értéke, viewInitnél lentebb már igen
    console.log('Text content:' + this.header.nativeElement.textContent);
    // Ugyanaz érvényes
    console.log(
      'Text content of paragraph:' + this.paragraph.nativeElement.textContent
    );
  }
  // ngOnInit után, azután csak változások esetén
  // Event hívás, promise értékvisszaadás, stb. esetén is
  // Mivel ez gyakran lefut, sok kódot nem érdemes adni neki
  ngDoCheck() {
    console.log('ngDoCheck called');
  }

  //1x miután megjelenítette a component html kódját
  ngAfterContentInit(): void {
    console.log('ngAfterContentInit called');
  }

  // ngAfterContentInit után, ezt követően doCheckek után
  ngAfterContentChecked(): void {
    console.log('ngAfterContentChecked called');
  }

  // ngAfterContentChecked után
  ngAfterViewInit(): void {
    console.log('ngAfterViewInit called');

    //Itt már van értéke, onInitnél fentebb még nem
    console.log('Text content:' + this.header.nativeElement.textContent);

    // Ugyanaz érvényes
    console.log(
      'Text content of paragraph:' + this.paragraph.nativeElement.textContent
    );
  }

  // ngAfterViewInit után
  ngAfterViewChecked(): void {
    console.log('ngAfterViewChecked called');
  }

  // Component DOM-ról való törlésekor
  ngOnDestroy(): void {
    console.log('ngOnDestroy called');
  }
}
