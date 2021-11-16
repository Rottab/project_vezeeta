import {Injectable} from '@angular/core';
import {Category} from "./category.model";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "src/environments/environment";


const headerDict = {
  'Content-Type': 'application/json',
  'Access-Control-Allow-Origin': '*',
}

const requestOptions = {
  headers: new HttpHeaders(headerDict),
};

const URL = `${environment.appURL}/categories`

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private categoryList: Category[] = []


  constructor(private http: HttpClient) {
  }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(URL, requestOptions)
  }
}
