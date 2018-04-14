import { Component, OnDestroy, OnInit } from '@angular/core';

import { Subscription } from "rxjs/Subscription";

import { MessageService } from "../shared/message/message.service";
import { environment } from '../../environments/environment';

@Component({
  templateUrl: './preview.component.html',
  styleUrls: ['./preview.component.css']
})
export class PreviewComponent implements OnInit, OnDestroy {

  token: string = '';
  previewUrl: string = '';

  appUrl: string = environment.appUrl + 'r/';

  private messageServiceSubscription: Subscription = null;

  constructor(private messageService: MessageService) { }

  ngOnInit(): void {
    this.messageServiceSubscription = this.messageService.message.subscribe(message => {
      if (message.type == 'preview') {
        this.previewUrl = '/api/render/' + message.subject.token;
        this.token = message.subject.token;
      }
    });
  }

  ngOnDestroy(): void {
    this.messageServiceSubscription.unsubscribe();
    this.token = '';
  }

}
