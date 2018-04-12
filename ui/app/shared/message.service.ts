import { Injectable } from '@angular/core';

import { BehaviorSubject } from "rxjs/BehaviorSubject";

@Injectable()
export class MessageService {

  private messageSource = new BehaviorSubject<string>("");

  message = this.messageSource.asObservable();

  constructor() { }

  send(message: string): void {
    this.messageSource.next(message)
  }

}
