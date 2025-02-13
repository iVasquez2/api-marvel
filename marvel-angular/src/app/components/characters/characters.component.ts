import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { MarvelService } from '../../services/marvel.service';
import { CommonModule } from '@angular/common';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-characters',
  templateUrl: './characters.component.html',
  styleUrls: ['./characters.component.css'],
  standalone: true,
  imports: [CommonModule]
})
export class CharactersComponent implements OnInit {
  characters: any[] = []; // Inicializamos como array vacío para evitar errores
  selectedCharacter: any = null; 

  @ViewChild('characterDetailModal') characterDetailModal!: TemplateRef<any>
  constructor(private marvelService: MarvelService,private modalService: NgbModal) {}

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
  openModal(character: any) {
    this.selectedCharacter = character;
    if (this.characterDetailModal) {
      console.log("Abriendo modal de detalle de personaje:", character);
      this.modalService.open(this.characterDetailModal, { size: 'lg', centered: true });
    } else {
      console.error("Error: Modal template no encontrado.");
    }
  }
}
