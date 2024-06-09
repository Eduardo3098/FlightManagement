import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
/**
 * Components
 */
import { AppComponent } from './app.component';
import { BaseComponent } from './components/base/base.component';
import { HomeScreenComponent } from './views/home-screen/home-screen.component';

@NgModule({
  declarations: [
    AppComponent,
    BaseComponent,
    HomeScreenComponent
  ],
  imports: [
    RouterModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
  ],
  exports: [AppComponent, BaseComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class SharedModule {}
