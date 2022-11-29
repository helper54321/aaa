import { Component, ElementRef, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';

@Component({
  selector: 'app-cockpit',
  templateUrl: './cockpit.component.html',
  styleUrls: ['./cockpit.component.css'],
})
export class CockpitComponent implements OnInit {
  // Itt event binding történik custom eventekhez, ahol megmondjuk hogy egy objektumot tartalmaz, és milyen típusokkal
  //Lentebb a functionoknál további magyarázat
  @Output() serverCreated = new EventEmitter<{
    serverName: string;
    serverContent: string;
  }>();
  // Itt, mint a server ts-ében is aliast adunk neki
  @Output("bpCreated") blueprintCreated = new EventEmitter<{
    serverName: string;
    serverContent: string;
  }>();


  //Kikommenteltem, mert korábban használtuk, később már más megoldás volt helyette
  // newServerName = '';
  // newServerContent = '';

  // A viewchild paraméterében a serverContentInput az a referencia, amit a html-ben #serverContentInput-el létrehoztunk az inputnál
  //Ezzel lényegében létrehoztunk egy referenciát arra a html elementre, és annak propertyjeit elérhetjük (kicsit hasonló a reactes useRef-re)
  //És az useRef-hez hasonlóan, itt se módosítsuk közvetlenül az értékét, esetleg kiolvassunk belőle!!
  @ViewChild('serverContentInput', {static: true}) serverContentInput: ElementRef;

  constructor() {}

  ngOnInit(): void {}

  //Itt pedig amikor a cockpit html-jében rákattintunk, akkor fog ez lefutni
  //Lényegében olyan mint reactnél amikor a parent functionját meghívja
  onAddServer(nameInput: HTMLInputElement) {
    console.log(nameInput.value)
    this.serverCreated.emit({
      // serverName: this.newServerName,
      serverName: nameInput.value,

      // serverContent: this.newServerContent,
      
      serverContent: this.serverContentInput.nativeElement.value,
    });
  }

  onAddBlueprint(nameInput: HTMLInputElement) {
    this.blueprintCreated.emit({
      // serverName: this.newServerName,
      serverName: nameInput.value,
      // serverContent: this.newServerContent,
      serverContent: this.serverContentInput.nativeElement.value,
    });
  }
}
