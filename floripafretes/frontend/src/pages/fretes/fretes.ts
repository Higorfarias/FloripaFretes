import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { FreteDTO } from '../../models/frete.dto';
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

  items: FreteDTO[];

  constructor(
    public navCtrl: NavController,
    public navParams: NavParams,
    public freteService: FreteService) {
  }

  ionViewDidLoad() {
    this.freteService.findAll().subscribe(response => {
      this.items = response;
    },
    error => {
      console.log(error);
    }
    
    );

  }

}
