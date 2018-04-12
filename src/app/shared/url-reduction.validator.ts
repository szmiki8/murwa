import { AbstractControl, AsyncValidatorFn } from "@angular/forms";
import { Injectable } from "@angular/core";

import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/of';

import { UrlReductionService } from "./url-reduction.service";

@Injectable()
export class UrlReductionValidator {

  constructor(private urlReductionService: UrlReductionService) { }

  uniqueToken(): AsyncValidatorFn {
    return (control: AbstractControl): Promise<{ [key: string]: any } | null> | Observable<{ [key: string]: any } | null> => {
      return new Promise((resolve, reject) => {
        if (control.value === null || control.value == '') {
          resolve(null);
        } else {
          this.urlReductionService.find(control.value).subscribe(response => {
            resolve({uniqueToken: true});
          }, error => {
            resolve(null);
          })
        }
      });
    }
  }

}
