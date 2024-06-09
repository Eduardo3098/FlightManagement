export interface IRouteBase {
  scope: string;
  type: any;
  urlResource: string;
  exposedModule: string;
  guards: string[];
  layout: string;
  fwr: string;
  name: string;
}
