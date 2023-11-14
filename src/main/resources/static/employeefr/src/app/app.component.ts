import { Component,HostListener } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
      title = 'employee';
      isGoodBrowser: boolean = false;
      isGoodResolution : boolean = false;

      constructor () {}

      ngOnInit (): void {
          if(navigator.userAgent.indexOf("Chrome") !=-1 || navigator.userAgent.indexOf("Safari") !=-1) {
            this.isGoodBrowser = true;
          }

          this.checkBrowserSize(window.innerWidth, window.innerHeight);
      }

      @HostListener('window:resize', ['$event'])
      onResize(event:any) {
        this.checkBrowserSize(event.target.innerWidth, event.target.innerHeight);
      }

      checkBrowserSize(width:number, height:number) {
          if((width >= 1440 && height >= 900) && (width <= 2560 && height <= 1440)) {
            this.isGoodResolution = true;
          } else {
            this.isGoodResolution = false;
          }
      }
}
