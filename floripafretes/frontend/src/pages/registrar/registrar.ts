import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

@IonicPage()
@Component({
  selector: 'page-registrar',
  templateUrl: 'registrar.html',
})
export class RegistrarPage {

  FormGroup: FormGroup;
 
  constructor(
    public navCtrl: NavController, 
    public navParams: NavParams,
    public formBuilder: FormBuilder) {

    this.formGroup = this.formBuilder.group({
      nome: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(80)]],
      email: ['', [Validators.required, Validators.email]],
      cpf : ['', [Validators.required]],
      senha : ['', [Validators.required, Validators.minLength(4), Validators.maxLength(20)]],
      celular : ['', []],     
    });

}

}
