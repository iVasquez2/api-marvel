import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MarvelService {
  private apiUrl = 'http://localhost:8080/marvel/characters';

  constructor(private http: HttpClient) {}

  getCharacters(limit: number = 21, offset: number = 0): Observable<any> {
    const headers = new HttpHeaders({
      'Authorization': 'Basic ' + btoa('admin:8956') // 🔹 Codifica usuario y contraseña en Base64
    });

    return this.http.get<any>(`${this.apiUrl}?limit=${limit}&offset=${offset}`, { headers });
  }

  getCharacterSeries(characterId: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${characterId}/series`);
  }
}
