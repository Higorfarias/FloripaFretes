import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AlertController, IonicPage, NavController, NavParams } from 'ionic-angular';
import { UsuarioService } from '../../services/domain/usuario.service';

@IonicPage()
@Component({
  selector: 'page-registrar',
  templateUrl: 'registrar.html',
})
export class RegistrarPage {

  formGroup: FormGroup;
 
  constructor(
    public navCtrl: NavController, 
    public navParams: NavParams,
    public alertCtrl: AlertController,
    public usuarioService: UsuarioService,
    public formBuilder: FormBuilder) {

    this.formGroup = this.formBuilder.group({
      nome: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(80)]],
      email: ['', [Validators.required, Validators.email]],
      cpf : ['', [Validators.required]],
      senha : ['', [Validators.required, Validators.minLength(4), Validators.maxLength(20)]],
      celular : ['', []],     
    });

  }
  registrarUser() {
    this.usuarioService.insert(this.formGroup.value)
      .subscribe(response => {
        this.showInsertOk();
      },
      error => {});
  }
  showInsertOk() {
    let alert = this.alertCtrl.create({
      title: 'Sucesso!',
      message: 'Cadastro efetuado com sucesso',
      enableBackdropDismiss: false,
      buttons: [
        {
          text: 'Ok',
          handler: () => {
            this.navCtrl.pop();
          }
        }
      ]
    });
    alert.present();
}
}

