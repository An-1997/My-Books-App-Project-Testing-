import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Book } from './book';
import { Observable } from 'rxjs/Observable';
import { map } from 'rxjs/operators/map';

export const InterceptorSkipHeader = 'X-Skip-Interceptor';

@Injectable()
export class BookService {

  imagePrefix:string;
  imageSuffix: string;
  watchListEndpoint: string;
  openLibraryEndpoint: string;

    constructor(private http: HttpClient ) { 
    this.imagePrefix='http://covers.openlibrary.org/b/id/';
    this.imageSuffix='-L.jpg';
    this.watchListEndpoint='http://localhost:8082/api/books/';
    this.openLibraryEndpoint='http://openlibrary.org/search.json?';
   }

  getBooksFromMyFavourites():Observable<Book[]>{
    return this.http.get<Book[]>(`${this.watchListEndpoint}getMyFavouriteBooks`);
  }

  addBookToMyFavourites(movie:Book):Observable<any>{
    return this.http.post(`${this.watchListEndpoint}saveBookToMyFavourites`,movie,{responseType:'text'});
  }

  deleteBookFromMyFavourites(movieId: number): Observable<any>{
    return this.http.delete(`${this.watchListEndpoint}deleteBookFromMyFavourites/${movieId}`,{responseType:'text'});
  }

  searchBooks(searchBook:string,searchType:string, page: number=1): Observable<Book[]>{
    const headers = new HttpHeaders().set(InterceptorSkipHeader, '');
    const searchEndPoint=`${this.openLibraryEndpoint}${searchType}=${searchBook}&page=${page}`;
    return this.http.get<Book[]>(searchEndPoint,{headers}).pipe(
      map(this.getBookResult),
      map(this.transformCoverPath.bind(this)));    
  }

  getBookResult(response) : Book[]{
    return response['docs'];
  }

  transformCoverPath(books) : Book[]{
    return books.filter(book => book.cover_i!== undefined)
    .map((book:Book)=> {  
      book.cover_i = `${this.imagePrefix}${book.cover_i}${this.imageSuffix}`;
      return book;
    })
  }
}
