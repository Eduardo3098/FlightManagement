import {Component, OnInit} from "@angular/core";
import {AuthService} from "@auth0/auth0-angular";
import {AuthService as AuthorizationService} from './services/auth/auth.service'
import {lastValueFrom} from "rxjs";
import {LoginInterface} from "./interfaces/login.interface";
import {Router} from "@angular/router";
import {Routes} from "./config/routes.enum";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
})
export class AppComponent implements OnInit {
  constructor(private auth: AuthService,
              private router: Router,
              private authService: AuthorizationService) {}

  async ngOnInit() {
    this.auth.isAuthenticated$.subscribe(async isAuthenticated => {
      if (isAuthenticated) {
        console.log('firstLogin', localStorage.getItem('userId'));
        if(localStorage.getItem('userId') === null) {
          const body: LoginInterface = {};
          this.auth.user$.subscribe(user => {
            body.name = user?.name!;
            body.userName = user?.["userName"]!;
            body.nickname = user?.["nickname"]!;
            body.roles = user?.["userRoles"];

            console.log('body', body);
            lastValueFrom(this.authService.postLogin(body))
                .then(value => {
                  localStorage.setItem('userId', value.userId)
                });
          });
        }
        this.router.navigate([Routes.FLIGHT_BOOKING]);
      } else {
        this.auth.loginWithRedirect();
      }
    });
  }

}
