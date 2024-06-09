import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { RoutingDetail } from "./config/router-mfa.config";
const routerDetail = new RoutingDetail();
let routesList: Routes = routerDetail.routerList();
@NgModule({
  imports: [RouterModule.forRoot(routesList)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
