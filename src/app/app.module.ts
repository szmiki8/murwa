import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MaterialModule } from './material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule }   from '@angular/forms';

import { AppComponent } from './app.component';
import { EditUrlReductionComponent } from "./edit/edit-url-reduction.component";
import { UrlReductionService } from "./shared/url-reduction.service";
import { HttpClientModule } from "@angular/common/http";
import { UrlReductionValidator } from "./shared/url-reduction.validator";
import { UrlReductionListComponent } from "./list/url-reduction-list.component";

@NgModule({
  declarations: [
    AppComponent,
    EditUrlReductionComponent,
    UrlReductionListComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule
  ],
  providers: [UrlReductionService, UrlReductionValidator],
  bootstrap: [AppComponent]
})
export class AppModule { }
