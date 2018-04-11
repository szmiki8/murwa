import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { UrlReductionValidator} from "../shared/url-reduction.validator";
import { UrlReductionService} from "../shared/url-reduction.service";
import { UrlReduction } from "../shared/url-reduction";

@Component({
  selector: 'edit-url-reduction',
  templateUrl: './edit-url-reduction.component.html',
  styleUrls: ['./edit-url-reduction.component.css']
})
export class EditUrlReductionComponent {

  form: FormGroup;

  @Output() urlReductionCreated = new EventEmitter();

  constructor(
    private formBuilder: FormBuilder,
    private urlReductionService: UrlReductionService,
    private urlReductionValidator: UrlReductionValidator
  ) {
    this.createForm();
  }

  onSubmit() {
    // TODO: check async validaton
    //if (this.form.valid) {
      const urlReduction = new UrlReduction(this.form.value);

      this.urlReductionService.create(urlReduction).subscribe(() => {
        this.urlReductionCreated.emit();
      });
    //}
  }

  private createForm() {
    this.form = this.formBuilder.group({
      url: ['', [Validators.required, Validators.minLength(1)]],
      token: ['', [Validators.minLength(1)], this.urlReductionValidator.uniqueToken()]
    });
  }

}
