import { Component, ViewChild } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'users-table',
  templateUrl: './users-table.component.html',
  styleUrls: ['../admin-page.component.css']
})
export class UsersTableComponent {

  users: User[];
  loadedPage = 0;
  usersButton = true;

  @ViewChild("file")
  file: any;

  constructor(private adminService: AdminService, private userService: UserService) {
    this.users = [];
  }

  ngOnInit() {
    this.loadUsers(this.loadedPage);
  }

  nextPage() {
    this.loadedPage += 1;
    this.loadUsers(this.loadedPage)
  }

  loadUsers(page: number) {
    this.adminService.getUsersPaginated(page).subscribe(
      loadedUsers => {
          this.users = this.users.concat(loadedUsers.content);
          this.usersButton = loadedUsers.numberOfElements != 0;
      },
      error => console.log(error)
    );
  }

  reload() {
    for (var i = 0; i <= this.loadedPage; i ++) {
      this.users = [];
      this.loadUsers(i);
    }
  }

  updateUser(user: User){
    this.adminService.updateUser(user).subscribe(
      _ => {
        this.adminService.deleteUserImage(user);
        this.uploadUserImage(user);
        this.reload();
      } ,
      error => console.error(error)
    );
  }

  deleteUser(user: User) {
    this.adminService.deleteUser(user).subscribe(
      _ => this.reload(),
      error => console.error(error)
    );
  }

  uploadUserImage(user: User): void {
    const image = this.file.nativeElement.files[0];
    if (image) {
      let formData = new FormData();
      formData.append("imageFile", image);
      this.adminService.setUserImage(user, formData).subscribe(
        response => {
          console.log(response);
          this.reload();
        },
        error => console.log('Error uploading book image: ' + error)
      );
    }
  }

  userImage(user: User) {
    if (user.image) {
      return '/api/admin/users/' + user.id + '/image';
    }
    else return
  }

}
