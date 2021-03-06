import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { UrlReductionValidator} from "../shared/url-reduction.validator";
import { UrlReductionService} from "../shared/url-reduction.service";
import { UrlReduction } from "../shared/url-reduction";
import { MessageService } from "../shared/message/message.service";
import { MatSnackBar } from "@angular/material";

@Component({
  selector: 'edit-url-reduction',
  templateUrl: './edit-url-reduction.component.html',
  styleUrls: ['./edit-url-reduction.component.css']
})
export class EditUrlReductionComponent {

  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private urlReductionService: UrlReductionService,
    private urlReductionValidator: UrlReductionValidator,
    private messageService: MessageService,
    private snackBar: MatSnackBar
  ) {
    this.createForm();
  }

  onSubmit(): void {
    if (this.form.valid) {
      const urlReduction = new UrlReduction(this.form.value);

      this.urlReductionService.create(urlReduction).subscribe((res) => {
        this.submitSuccess();
      });
    }
  }

  private submitSuccess(): void {
    this.messageService.send('created');
    this.form.reset({url: 'https://'});
    this.snackBar.open('URL reduced!', 'Roger that!');
  }

  private createForm(): void {
    this.form = this.formBuilder.group({
      url: ['https://', [
        Validators.required,
        Validators.maxLength(1000),
        Validators.pattern("^(https?:\\/\\/)[\\w\\-]+(\\.[\\w\\-]+)*(\\/[\\w\\-\\?\\#\\&\\.\\=\\/]*)?$")
      ]],
      token: ['', [
        Validators.minLength(1),
        Validators.maxLength(255),
        Validators.pattern("^[0-9a-zA-Z]*$")
      ],
        this.urlReductionValidator.uniqueToken()
      ]
    });
  }

}
