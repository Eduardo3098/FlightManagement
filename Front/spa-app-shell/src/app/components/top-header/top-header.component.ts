import {Component, OnInit} from '@angular/core';
import {AuthService, User} from "@auth0/auth0-angular";
import {lastValueFrom} from "rxjs";
import {AuthService as AuthorizationService} from "../../services/auth/auth.service";

@Component({
  selector: 'app-top-header',
  templateUrl: './top-header.component.html',
  styleUrls: ['./top-header.component.scss']
})


export class TopHeaderComponent implements OnInit {

  user: User | null | undefined;
  constructor(public auth: AuthService,
              private authService: AuthorizationService) {
  }

  async ngOnInit() {
    this.auth.user$.subscribe(user => {
      this.user = user;
    })
  }

  logOut() {
    lastValueFrom(this.authService.postLogout(localStorage.getItem('userId')!))
        .then(() => {
          localStorage.clear();
          this.auth.logout();
        });
  }
}
