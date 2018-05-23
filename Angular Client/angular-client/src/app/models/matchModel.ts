// To parse this data:
//
//   import { Convert } from "./file";
//
//   const matchModel = Convert.toMatchModel(json);

export interface MatchModel {
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
  leagueName: LeagueName;
  playedGames: number;
  points: number;
  goals: number;
  img: string;
}

export enum LeagueName {
  PremierLeague = 'Premier League',
}

// Converts JSON strings to/from your types
export namespace Convert {
  export function toMatchModel(json: string): MatchModel[] {
    return JSON.parse(json);
  }

  export function matchModelToJson(value: MatchModel[]): string {
    return JSON.stringify(value, null, 2);
  }
}
