export interface BetModel {
  id: number;
  user: User;
  betRate: BetRate;
  buyDateAndTime: string;
  moneyInserted: number;
  betType: string;
  amountToPaidOut: number;
  betResult: number;
  matches: Matches;
}

export interface BetRate {
  id: number;
  homeTeamWinRate: number;
  awayTeamWinRate: number;
  drawRate: number;
  homeTeamWinOrDrawRate: number;
  awayTeamWinOrDrawRate: number;
  homeWinOrAwayWin: number;
}

export interface Matches {
  id: number;
  homeTeamId: TeamID;
  awayTeamId: TeamID;
  homeTeamScore: number;
  awayTeamScore: number;
  matchDateAndTime: string;
}

export interface TeamID {
  id: number;
  name: string;
  teamStatistics: TeamStatistics;
}

export interface TeamStatistics {
  id: number;
  wonMatches: number;
  lostMatches: number;
  drawMatches: number;
  leagueName: string;
  playedGames: number;
  points: number;
  goals: number;
  img: string;
}

export interface User {
  id: number;
  userStatistics: UserStatistics;
  name: string;
  surname: string;
  birthDate: string;
  address: string;
  postCode: string;
  login: string;
  password: string;
}

export interface UserStatistics {
  id: number;
  wonMatches: number;
  lostMatches: number;
  biggestWin: number;
  biggestWinDate: string;
  accountBalance: number;
}

// Converts JSON strings to/from your types
export namespace ConvertBetModel {
  export function toBetModel(json: string): BetModel[] {
    return JSON.parse(json);
  }

  export function betModelToJson(value: BetModel[]): string {
    return JSON.stringify(value, null, 2);
  }
}
