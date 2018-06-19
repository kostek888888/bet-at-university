import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'translateTeamsNames'
})
export class TranslateTeamsNamesPipe implements PipeTransform {

  teams = [
            {engName: 'Russia', plName: 'Rosja'},
            {engName: 'Brazil', plName: 'Brazylia'},
            {engName: 'Iran', plName: 'Iran'},
            {engName: 'Japan', plName: 'Japonia'},
            {engName: 'Mexico', plName: 'Meksyk'},
            {engName: 'Belgium', plName: 'Belgia'},
            {engName: 'South Korea', plName: 'Korea Płd'},
            {engName: 'Saudi Arabia', plName: 'Arabia Saudyjska'},
            {engName: 'Germany', plName: 'Niemcy'},
            {engName: 'England', plName: 'Anglia'},
            {engName: 'Spain', plName: 'Hiszpania'},
            {engName: 'Nigeria', plName: 'Nigeria'},
            {engName: 'Costa Rica', plName: 'Kostaryka'},
            {engName: 'Poland', plName: 'Polska'},
            {engName: 'Egypt', plName: 'Egipt'},
            {engName: 'Iceland', plName: 'Islandia'},
            {engName: 'Serbia', plName: 'Serbia'},
            {engName: 'Portugal', plName: 'Portugalia'},
            {engName: 'France', plName: 'Francja'},
            {engName: 'Uruguay', plName: 'Urugwaj'},
            {engName: 'Argentina', plName: 'Argentyna'},
            {engName: 'Colombia', plName: 'Kolumbia'},
            {engName: 'Panama', plName: 'Panama'},
            {engName: 'Senegal', plName: 'Senegal'},
            {engName: 'Morocco', plName: 'Maroko'},
            {engName: 'Tunisia', plName: 'Tunezja'},
            {engName: 'Switzerland', plName: 'Szwajcaria'},
            {engName: 'Croatia', plName: 'Chorwacja'},
            {engName: 'Sweden', plName: 'Szwecja'},
            {engName: 'Denmark', plName: 'Dania'},
            {engName: 'Australia', plName: 'Australia'},
            {engName: 'Peru', plName: 'Peru'}
          ];
  transform(value: string, args?: any): string {
   // value = this.teams.find(x => x.engName === value).plName;
    return value;
  }

}
