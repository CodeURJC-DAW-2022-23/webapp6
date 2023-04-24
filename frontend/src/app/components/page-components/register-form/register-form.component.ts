import { Component, EventEmitter, Output } from '@angular/core';
import { LoginService } from '../../../services/login.service';
import { UserService } from '../../../services/user.service';

@Component({
  selector: 'register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent {

  username: string = '';
  password: string = '';
  email: string = '';
  selectedFile: File | null = null;

  constructor(public loginService: LoginService, private userService: UserService) { }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  @Output() register = new EventEmitter<{ username: string, password: string, email: string }>();

  submitRegisterForm() {
    console.log('Form submitted');
    this.register.emit({ username: this.username, password: this.password, email: this.email });

  }

  updateProfileImage() {
    if (this.selectedFile) {
      this.userService.updateProfileImage(this.selectedFile).subscribe(
        (response) => {
          console.log('Imagen de perfil actualizada con éxito');
        },
        (error) => {
          console.error('Error al actualizar la imagen de perfil:', error);
        }
      );
    }
  }

}
