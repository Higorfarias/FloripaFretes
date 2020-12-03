import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { FreteDTO } from '../../models/frete.dto';
import { FreteService } from '../../services/domain/frete.service';

/**
 * Generated class for the FreteDetailPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-frete-detail',
  templateUrl: 'frete-detail.html',
})
export class FreteDetailPage {

  item: FreteDTO;

  constructor(
    public navCtrl: NavController, 
    public navParams: NavParams,
    public freteService: FreteService) {
  }

  ionViewDidLoad() {
    let frete_id = this.navParams.get('frete_id');
    this.freteService.findById(frete_id)
      .subscribe(response => {
        this.item = response;
      },
      error => {});
  }
}

