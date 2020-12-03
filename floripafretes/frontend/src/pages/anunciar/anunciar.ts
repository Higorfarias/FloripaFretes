import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AlertController, IonicPage, NavController, NavParams } from 'ionic-angular';
import { FreteService } from '../../services/domain/frete.service';

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
    public alertCtrl: AlertController,
    public freteService: FreteService,
    public formBuilder: FormBuilder) {

    this.formGroup = this.formBuilder.group({
      titulo: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(20)]],
      descricao: ['', [Validators.required, Validators.maxLength(500)]],
      cep : ['', [Validators.required]],
      endereco : ['', [Validators.required, Validators.maxLength(80)]],
      celular : ['', []],     
    });
  }

  anunciarFrete() {
    this.freteService.insert(this.formGroup.value)
      .subscribe(response => {
        this.showInsertOk();
      },
      error => {});
  }
  showInsertOk() {
    let alert = this.alertCtrl.create({
      title: 'Sucesso!',
      message: 'Frete anunciado',
      enableBackdropDismiss: false,
      buttons: [
        {
          text: 'Ok',
          handler: () => {
            this.navCtrl.setRoot("FretesPage");
          }
        }
      ]
    });
    alert.present();
}

}
