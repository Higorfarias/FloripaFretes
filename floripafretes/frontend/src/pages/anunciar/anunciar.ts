import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

@IonicPage()
@Component({
  selector: 'page-anunciar',
  templateUrl: 'anunciar.html',
})
export class AnunciarPage {

  formGroup: FormGroup;
 
  constructor(
    public navCtrl: NavController, 
    public navParams: NavParams,
    public formBuilder: FormBuilder) {

    this.formGroup = this.formBuilder.group({
      titulo: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(20)]],
      descricao: ['', [Validators.required, Validators.maxLength(100)]],
      cep : ['', [Validators.required]],
      endereco : ['', [Validators.required, Validators.maxLength(80)]],
      celular : ['', []],     
    });



}

}
