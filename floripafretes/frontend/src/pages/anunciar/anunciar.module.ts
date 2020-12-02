import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { AnunciarPage } from './anunciar';

@NgModule({
  declarations: [
    AnunciarPage,
  ],
  imports: [
    IonicPageModule.forChild(AnunciarPage),
  ],
})
export class AnunciarPageModule {}
