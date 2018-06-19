// To parse this data:
//
//   import { Convert } from "./file";
//
//   const leagueTableModel = Convert.toLeagueTableModel(json);

export interface LeagueTableModel {
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
  group: string;
}

export enum LeagueName {
  PremierLeague = 'Premier League',
}

// Converts JSON strings to/from your types
export namespace Convert {
  export function toLeagueTableModel(json: string): LeagueTableModel {
    return JSON.parse(json);
  }

  export function leagueTableModelToJson(value: LeagueTableModel): string {
    return JSON.stringify(value, null, 2);
  }
}
