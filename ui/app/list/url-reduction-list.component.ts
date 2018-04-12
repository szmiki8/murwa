import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from "rxjs/Subscription";
import { environment } from '../../environments/environment';

import { UrlReductionService } from "../shared/url-reduction.service";
import { MessageService } from "../shared/message.service";

@Component({
  selector: 'url-reduction-list',
  templateUrl: './url-reduction-list.component.html',
  styleUrls: ['./url-reduction-list.component.css']
})
export class UrlReductionListComponent implements OnInit, OnDestroy {

  columns: any = ['shortUrl', 'url'];
  urlReductions: Array<any> = [];
  appUrl: string = environment.appUrl;

  private messageServiceSubscription: Subscription = null;

  constructor(
    private urlReductionService: UrlReductionService,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.messageServiceSubscription = this.messageService.message.subscribe(message => {
      this.loadData();
    });
    this.loadData();
  }

  ngOnDestroy(): void {
    this.messageServiceSubscription.unsubscribe();
  }

  private loadData(): void {
    this.urlReductionService.findAll().subscribe(response => {
      this.urlReductions = response;
    });
  }

}
