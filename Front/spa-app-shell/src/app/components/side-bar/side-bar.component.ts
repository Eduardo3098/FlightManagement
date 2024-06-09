import {Component, OnInit} from '@angular/core';
import {SidebarInterface} from "../../interfaces/sidebar.interface";
import {sidebar} from "../../services/menu/const.menu";
import {AuthService} from "@auth0/auth0-angular";

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.scss']
})
export class SideBarComponent implements OnInit{
  sidebar: SidebarInterface[] = [];
  sidebarCopy: SidebarInterface[] = [...sidebar];
  public hiden: boolean = false;

  constructor(public auth: AuthService) {}

  ngOnInit(): void {
    this.auth.user$.subscribe(user => {
      console.log('u  s', user)
      // @ts-ignore
      user['userRoles'].forEach((userRole: string) => {
        this.sidebarCopy.forEach(item => {
          if(item.role?.toString().toLowerCase().includes(userRole.toLowerCase())) {
            this.sidebar.push(item);
          }
        });
      });
    })
  }
}
