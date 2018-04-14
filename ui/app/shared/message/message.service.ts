import { Injectable } from '@angular/core';

import { BehaviorSubject } from "rxjs/BehaviorSubject";

import { Message } from "./message";

@Injectable()
export class MessageService {

  private messageSource = new BehaviorSubject<Message>(null);

  message = this.messageSource.asObservable();

  constructor() { }

  send(type: string, subject: any = {}): void {
    this.messageSource.next(new Message(type, subject));
  }

}
