import { Component } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CarouselModule } from 'ngx-bootstrap/carousel';
import { ModalModule } from 'ngx-bootstrap/modal';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';  // Importar RouterModule

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [HttpClientModule, CarouselModule, ModalModule, NgbModule, RouterModule],  // Añadir RouterModule
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'marvel-angular'
  constructor(private http: HttpClient) {}
}
