import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MaterialModule } from './material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule }   from '@angular/forms';
import { HttpClientModule } from "@angular/common/http";

import { AppComponent } from './app.component';
import { UrlReductionService } from "./shared/url-reduction.service";
import { UrlReductionValidator } from "./shared/url-reduction.validator";
import { MessageService } from "./shared/message/message.service";
import { ModalService } from "./shared/modal.service";
import { EditUrlReductionComponent } from "./edit/edit-url-reduction.component";
import { UrlReductionListComponent } from "./list/url-reduction-list.component";
import { PreviewComponent } from "./preview/preview.component";

@NgModule({
  declarations: [
    AppComponent,
    EditUrlReductionComponent,
    UrlReductionListComponent,
    PreviewComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule
  ],
  providers: [
    UrlReductionService,
    UrlReductionValidator,
    MessageService,
    ModalService
  ],
  entryComponents: [
    PreviewComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
