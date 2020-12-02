import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';

import { MyApp } from './app.component';

import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { FreteService } from '../services/domain/frete.service';
import { AuthService } from '../services/auth.service';
import { StorageService } from '../services/storage.service';
import { UsuarioService } from '../services/domain/usuario.service';
import { ErrorInterceptorProvider } from '../config/interceptors/error.interceptor';


@NgModule({
  declarations: [
    MyApp
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    IonicModule.forRoot(MyApp),
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    FreteService,
    AuthService,
    StorageService,
    UsuarioService,
    ErrorInterceptorProvider
  ]
})
export class AppModule {}
