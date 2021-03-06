import { Injectable } from '@angular/core';
import {  HttpInterceptor,
          HttpRequest,
          HttpHandler,
          HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { AuthenticationService } from '../authentication/authentication.service';

export const InterceptorSkipHeader = 'X-Skip-Interceptor';

@Injectable()
export class TokeninterceptorService implements HttpInterceptor{

  constructor(private authService: AuthenticationService) { }

  intercept(request:HttpRequest<any>,next: HttpHandler): Observable<HttpEvent<any>>{
    console.log('In intercept service');
    if (!request.headers.has(InterceptorSkipHeader)) {    
      request= request.clone({
      headers:request.headers.set(        
        'Authorization', `Bearer ${this.authService.getToken()}`
      )     
    });
    }else{
     request= request.clone({
       headers:request.headers.delete(InterceptorSkipHeader)     
     });
    }
    return next.handle(request);
  }

}
