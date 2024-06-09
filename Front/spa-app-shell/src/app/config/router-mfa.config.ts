import { IRouteBase } from "./interfaces/routes.interface";
import { Guards, ListLayout } from "./layouts";

import routesImport from "../../assets/microapp.json";
import { loadRemoteModule } from "@angular-architects/module-federation";
import {Route, Routes} from "@angular/router";
import { EmptyComponent } from "../components/empty/empty.component";
import {startsWith, WebComponentWrapper, WebComponentWrapperOptions} from "@angular-architects/module-federation-tools";

export class RoutingDetail {
  baseRoute: Route = {
    path: "**",
    redirectTo: "flight-booking",
    pathMatch: "full",
  };

  async routerModule(value: Partial<IRouteBase>) {
    try {
      const moduleResult = await loadRemoteModule({
        type: value.type,
        remoteEntry: value.urlResource || "",
        exposedModule: value.exposedModule || "",
      });

      return moduleResult.MFModule;
    } catch (error) {
      location.href = "/";
    }
  }

  routerList() {
    const mapRoutes: Routes = routesImport.map((value: Partial<IRouteBase>) => {
      let { layout, guards, scope } = value;
      layout = layout || "dashboard";
      guards = guards || [];
      return {
        path: scope,
        component: ListLayout[layout],
        children:
            value.fwr !== "react"
                ? [
                  {
                    path: "",
                    loadChildren: () => this.routerModule(value),
                    canActivate: guards.map((guard: string) => {
                      return Guards[guard];
                    }),
                  },
                  {
                    ...this.baseRoute,
                  },
                ]
                : [
                  {
                    matcher: startsWith(""),
                    component: WebComponentWrapper,
                    data: {
                      type: value.type,
                      remoteEntry: value.urlResource || "",
                      remoteName: "react",
                      exposedModule: value.exposedModule || "",
                      elementName: value.exposedModule?.replace("./", ""),
                    } as WebComponentWrapperOptions,
                  },
                ],
      };
    });
    return [
      {
        path: "",
        component: EmptyComponent,
      },
      {
        path: "state",
        component: EmptyComponent,
      },
      {
        path: "no-authorized",
        component: EmptyComponent,
      },
      ...mapRoutes,
      this.baseRoute,
    ];
  }

  mapRouteAngular = (value: Partial<IRouteBase>): Route => {
    let { layout, guards, scope } = value;
    layout = layout ?? "dashboard";
    guards = guards ?? [];
    return {
      path: scope,
      component: ListLayout[layout],
      children: [
        {
          path: "",
          loadChildren: () => this.routerModule(value),
          canActivate: guards.map((guard: string) => {
            return Guards[guard];
          }),
        },
        {
          ...this.baseRoute,
        },
      ],
    };
  };
}
