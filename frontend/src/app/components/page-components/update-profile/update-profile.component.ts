import { Component } from '@angular/core';
import { UserService } from '../../../services/user.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent {
  newEmail: string = '';
  selectedFile: File | null = null;

  constructor(private userService: UserService, private router: Router) {}

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  updateUserEmail() {
    this.userService.updateUserEmail(this.newEmail).subscribe(
      (updatedUser) => {
        console.log('Usuario actualizado:', updatedUser);
      },
      (error) => {
        console.error('Error al actualizar el usuario:', error);
      }
    );
  }

  updateProfileImage() {
    if (this.selectedFile) {
      this.userService.updateProfileImage(this.selectedFile).subscribe(
        (response) => {
          console.log('Imagen de perfil actualizada con éxito');
          this.router.navigate(['/user-page']);
        },
        (error) => {
          console.error('Error al actualizar la imagen de perfil:', error);
          this.router.navigate(['/user-page']);
        }
      );
    }
  }
}
