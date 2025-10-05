/* tslint:disable */
/* eslint-disable */
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

/**
 * Global configuration
 */
@Injectable({
  providedIn: 'root',
})
export class ApiConfiguration {
  /** Root URL for the backend API */
  rootUrl: string = environment.apiUrl;
}

/**
 * Parameters for `ApiModule.forRoot()`
 */
export interface ApiConfigurationParams {
  rootUrl?: string;
}