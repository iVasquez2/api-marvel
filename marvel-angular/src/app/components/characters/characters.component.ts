import { Component, OnInit } from '@angular/core';
import { MarvelService } from '../../services/marvel.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-characters',
  templateUrl: './characters.component.html',
  standalone: true,
  imports: [CommonModule]
})
export class CharactersComponent implements OnInit {
  characters: any[] = []; // Inicializamos como array vacío para evitar errores

  constructor(private marvelService: MarvelService) {}

  ngOnInit() {
    this.marvelService.getCharacters().subscribe({
      next: (data) => {
        console.log("Respuesta de API:", data); // Verifica en consola la estructura de respuesta
        this.characters = data?.data?.results || []; // Si `results` es undefined, asignamos un array vacío
        console.log("Personajes cargados:", this.characters);
      },
      error: (error) => {
        console.error('Error al obtener los personajes:', error);
        this.characters = []; // Evita que el componente falle si hay un error
      }
    });
  }
}
