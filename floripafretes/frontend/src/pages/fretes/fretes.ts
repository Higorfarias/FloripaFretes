import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { FreteService } from '../../services/domain/frete.service';

/**
 * Generated class for the FretesPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-fretes',
  templateUrl: 'fretes.html',
})
export class FretesPage {

  constructor(
    public navCtrl: NavController,
    public navParams: NavParams,
    public freteService: FreteService) {
  }

  ionViewDidLoad() {
    this.freteService.findAll().subscribe(response => {
      console.log(response);
    },
    error => {
      console.log(error);
    }
    
    );

  }

}
