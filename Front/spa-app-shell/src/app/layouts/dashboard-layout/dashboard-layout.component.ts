import { Component, OnDestroy, OnInit } from "@angular/core";
/**
 * Interfaces
 */

@Component({
  selector: "app-dashboard-layout",
  templateUrl: "./dashboard-layout.component.html",
  styleUrls: ["./dashboard-layout.component.scss"],
})
export class DashboardLayoutComponent {
  hiden: boolean = false;

  constructor(
  ) {}

  ngOnDestroy(): void {
  }
  ngOnInit(): void {

  }
}
