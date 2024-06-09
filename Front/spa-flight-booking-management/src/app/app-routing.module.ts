import { Routes } from '@angular/router';

import { BaseComponent } from './components/base/base.component';
import { HomeScreenComponent } from './views/home-screen/home-screen.component';

export const routes: Routes = [{
  path: '',
  component: BaseComponent,
  children: [
    {path:"",component:HomeScreenComponent}
  ]
}];

