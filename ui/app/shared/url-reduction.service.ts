import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

import { Observable } from 'rxjs/Observable';
import { UrlReduction } from "./url-reduction";

@Injectable()
export class UrlReductionService {

  private readonly URL = "/api/url_reduction";

  constructor(protected client: HttpClient) { }

  public create(urlReduction: UrlReduction) {
    return this.client.post(this.URL, urlReduction);
  }

  public find(token: string): Observable<UrlReduction> {
    return this.client.get<UrlReduction | null>(`${this.URL}/${token}`);
  }

  public findAll(): Observable<UrlReduction[]> {
    return this.client.get<UrlReduction[]>(this.URL);
  }

}
