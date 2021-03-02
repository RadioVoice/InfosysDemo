import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-payment-form',
  templateUrl: './payment-form.component.html',
  styleUrls: ['./payment-form.component.css']
})
export class PaymentFormComponent {
  
  angForm: FormGroup;
  
  constructor(private fb: FormBuilder) {
    this.createForm();
  }
  
  createForm() {
    this.angForm = this.fb.group({
      cardNum: ['', Validators.required ],
      cardName: ['', Validators.required ],
      expDate: ['', Validators.required ],
      cvv: ['', [Validators.required,  Validators.minLength(3)] ],
   });
  }

}
