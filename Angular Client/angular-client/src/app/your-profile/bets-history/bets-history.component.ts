import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-bets-history',
  templateUrl: './bets-history.component.html',
  styleUrls: ['./bets-history.component.css']
})
export class BetsHistoryComponent implements OnInit {

  formObj = new FormModel();
  constructor() { }

  ngOnInit() {
  }
  getSearchBets() {
    console.log('search works');
  }

}

export class FormModel {
  searchText?: string;
  filter = 'noFilter';
}
