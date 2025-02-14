import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { MarvelService } from '../../services/marvel.service';
import { CommonModule } from '@angular/common';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CarouselModule } from 'ngx-bootstrap/carousel';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-characters',
  templateUrl: './characters.component.html',
  styleUrls: ['./characters.component.css'],
  standalone: true,
  imports: [CommonModule, CarouselModule, NgFor]
})
export class CharactersComponent implements OnInit {
  characters: any[] = []; // Inicializamos como array vacío para evitar errores
  selectedCharacter: any = null;
  characterSeries: any[] = [];
  currentPage: number = 1;
  totalCharacters: number = 0;
  limit: number = 21; // Número de personajes por página
  totalPages: number = 0;

  @ViewChild('characterDetailModal') characterDetailModal!: TemplateRef<any>;

  constructor(private marvelService: MarvelService, private modalService: NgbModal) {}

  ngOnInit() {
    this.loadCharacters();
  }

  loadCharacters() {
    const offset = (this.currentPage - 1) * this.limit;

    this.marvelService.getCharacters(this.limit, offset).subscribe({
      next: (data) => {
        console.log("Respuesta de API:", data); // Verifica en consola la estructura de respuesta
        this.characters = data?.data?.results || [];
        this.totalCharacters = data?.data?.total || 0;
        this.totalPages = Math.ceil(this.totalCharacters / this.limit);
        console.log("Personajes cargados:", this.characters);
      },
      error: (error) => {
        console.error('Error al obtener los personajes:', error);
        this.characters = []; // Evita que el componente falle si hay un error
      }
    });
  }

  nextPage() {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.loadCharacters();
    }
  }

  prevPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.loadCharacters();
    }
  }

  openModal(character: any) {
    this.selectedCharacter = character;
    if (this.characterDetailModal) {
      console.log("Abriendo modal de detalle de personaje:", character);
      this.modalService.open(this.characterDetailModal, { size: 'lg', centered: true });
      console.log();
  
      // Asegúrate de que el `id` del personaje sea válido
      if (this.selectedCharacter?.id) {
        this.marvelService.getCharacterSeries(this.selectedCharacter.id).subscribe({
          next: (data) => {
            this.characterSeries = data?.data?.results || [];
            console.log("Series cargadas:", this.characterSeries);
          },
          error: (error) => {
            console.error('Error al obtener las series:', error);
            this.characterSeries = [];
          }
        });
      } else {
        console.error('Error: El ID del personaje es undefined.');
      }
    } else {
      console.error("Error: Modal template no encontrado.");
    }
  }
  
}
