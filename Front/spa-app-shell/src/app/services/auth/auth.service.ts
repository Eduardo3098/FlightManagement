import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginInterface, LoginResponseInterface} from "../../interfaces/login.interface";
import {environment} from "../../../environments/environment";
import {Endpoints} from "../../config/endpoints.enum";
import {generate} from "../encryption/encrypt";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient) { }

  postLogin(login: LoginInterface) {
    return this.httpClient.post<LoginResponseInterface>(environment.apiUrl + Endpoints.LOGIN, generate(login));
  }

  postLogout(userId: string) {
    const logout: LoginResponseInterface = { userId: userId };
    return this.httpClient.post(environment.apiUrl + Endpoints.LOGOUT, generate(logout));
  }
}
