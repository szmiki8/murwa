import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from "rxjs/Subscription";

import { UrlReductionService } from "../shared/url-reduction.service";
import { MessageService } from "../shared/message/message.service";
import { PreviewComponent } from "../preview/preview.component";
import { ModalService } from "../shared/modal.service";

@Component({
  selector: 'url-reduction-list',
  templateUrl: './url-reduction-list.component.html',
  styleUrls: ['./url-reduction-list.component.css']
})
export class UrlReductionListComponent implements OnInit, OnDestroy {

  columns: any = ['shortUrl', 'url'];
  urlReductions: Array<any> = [];

  private messageServiceSubscription: Subscription = null;

  constructor(
    private urlReductionService: UrlReductionService,
    private messageService: MessageService,
    private modalService: ModalService
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

  onRowClicked(row: any): void {
    this.modalService.open(PreviewComponent);
    this.messageService.send('preview', row);
  }

  private loadData(): void {
    this.urlReductionService.findAll().subscribe(response => {
      this.urlReductions = response;
    });
  }

}
