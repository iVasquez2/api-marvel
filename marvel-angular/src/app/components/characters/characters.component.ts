import { Component, OnInit } from '@angular/core';
import { MarvelService } from '../../services/marvel.service';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-characters',
  templateUrl: './characters.component.html',
  standalone: true,
  imports: [CommonModule, HttpClientModule]
})
export class CharactersComponent implements OnInit {
  characters: any[] = [];

  constructor(private marvelService: MarvelService) {}

  ngOnInit() {
    this.marvelService.getCharacters().subscribe({
      next: (data) => {
        this.characters = data.results; // 🔹 Ajusta esto según el formato de respuesta de tu API
      },
      error: (error) => {
        console.error('Error al obtener los personajes:', error);
      }
    });
  }
}
