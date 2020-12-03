import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { FreteDTO } from '../../models/frete.dto';
import { FreteService } from '../../services/domain/frete.service';

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

  showDetail(frete_id : string) {
    this.navCtrl.push('FreteDetailPage', {frete_id: frete_id});
  }

}
