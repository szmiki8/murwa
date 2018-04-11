import { AbstractControl, AsyncValidatorFn } from "@angular/forms";

import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/of';

import { UrlReductionService } from "./url-reduction.service";
import { Injectable } from "@angular/core";

@Injectable()
export class UrlReductionValidator {

  constructor(private urlReductionService: UrlReductionService) { }

  uniqueToken(): AsyncValidatorFn {
    return (control: AbstractControl): Promise<{ [key: string]: any } | null> | Observable<{ [key: string]: any } | null> => {
      return new Observable(observer => {
        if(control.value === null || control.value == "") {
          observer.next(null);
        } else {
          this.urlReductionService.find(control.value).subscribe(response => {
            observer.next(response ? {uniqueToken: true} : null);
          }, error => {
            observer.next(null);
          });
        }
      });
    }
  }

}
