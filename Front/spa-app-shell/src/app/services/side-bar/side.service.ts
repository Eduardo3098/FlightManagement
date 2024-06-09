import { Injectable,EventEmitter } from '@angular/core';
@Injectable({providedIn:'root'})
export class SideService {  constructor() {}


public activeSide$ = new EventEmitter<boolean>();

public disableSideHeaderFooter = new EventEmitter<boolean>();


}


