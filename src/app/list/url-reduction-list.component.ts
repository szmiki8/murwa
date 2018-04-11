import { Component, OnInit } from '@angular/core';

import { UrlReductionService } from "../shared/url-reduction.service";

import { environment } from '../../environments/environment';

@Component({
  selector: 'url-reduction-list',
  templateUrl: './url-reduction-list.component.html',
  styleUrls: ['./url-reduction-list.component.css']
})
export class UrlReductionListComponent implements OnInit {

  columns: any = ['shortUrl', 'url'];
  urlReductions: any = [];
  appUrl: string = environment.appUrl;

  constructor(private urlReductionService: UrlReductionService) { }

  ngOnInit() {
    this.loadData();
  }

  private loadData() {
    this.urlReductionService.findAll().subscribe(response => {
      this.urlReductions = response;
    });
  }

}
