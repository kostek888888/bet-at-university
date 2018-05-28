export interface User {
  id: number;
  name: string;
  surname: string;
  birthDate: Date;
  address: string;
  postCode: string;
  city: string;
}

export interface LoginModel {
  login: string;
  password: string;
}
