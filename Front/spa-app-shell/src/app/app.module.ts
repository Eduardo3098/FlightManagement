import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { AppRoutingModule } from "./app-routing.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

/**
 * Components
 */
import { AppComponent } from "./app.component";
import { DashboardLayoutComponent } from "./layouts/dashboard-layout/dashboard-layout.component";

/**
 * Guard
 */
import { EmptyComponent } from "./components/empty/empty.component";
import {provideAuth0} from "@auth0/auth0-angular";
import { TopHeaderComponent } from './components/top-header/top-header.component';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import {AuthService} from "./services/auth/auth.service";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    DashboardLayoutComponent,
    EmptyComponent,
    TopHeaderComponent,
    SideBarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    provideAuth0({
      domain: 'dev-95oiydpn.us.auth0.com',
      clientId: 'PhhJ9Ro78qlTqx6dLSR6mzrmCShU4Ml7',
      authorizationParams: {
        redirect_uri: window.location.origin
      }
    }),
    AuthService
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class AppModule {}
