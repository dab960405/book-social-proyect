import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiConfiguration } from '../api-configuration';

@Injectable({
  providedIn: 'root'
})
export class CustomBookService {
  
  constructor(
    private http: HttpClient,
    private config: ApiConfiguration
  ) {}

  /**
   * Elimina permanentemente un libro
   */
  deleteBook(params: { 'book-id': number }): Observable<void> {
    return this.http.delete<void>(`${this.config.rootUrl}/books/${params['book-id']}`);
  }
}