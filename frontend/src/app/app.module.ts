import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './components/app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';

//Import all components
//Import routing

@NgModule({
  declarations: [
    AppComponent, HeaderComponent, FooterComponent
    //All components
  ],
  imports: [
    BrowserModule, FormsModule, HttpClientModule,
    //Add routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
