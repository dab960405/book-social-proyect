import { Component, OnInit } from '@angular/core';
import { TokenService } from '../../../../services/token/token.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {
  userFullName: string = '';

  constructor(private tokenService: TokenService) {}

  ngOnInit(): void {
    // Obtener el nombre del usuario
    this.userFullName = this.tokenService.userFullName;

    // Tu código existente para los links activos
    const linkColor = document.querySelectorAll('.nav-link');
    linkColor.forEach(link => {
      if (window.location.href.endsWith(link.getAttribute('href') || '')) {
        link.classList.add('active');
      }
      link.addEventListener('click', () => {
        linkColor.forEach(l => l.classList.remove('active'));
        link.classList.add('active');
      });
    });
  }

  logout() {
    localStorage.removeItem('token');
    window.location.reload();
  }
}