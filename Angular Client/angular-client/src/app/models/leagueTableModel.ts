// To parse this data:
//
//   import { Convert, LeagueTableModel } from "./file";
//
//   const leagueTableModel = Convert.toLeagueTableModel(json);

export interface LeagueTableModel {
  _links: LeagueTableModelLinks;
  leagueCaption: string;
  matchday: number;
  standing: Standing[];
}

export interface LeagueTableModelLinks {
  self: Competition;
  competition: Competition;
}

export interface Competition {
  href: string;
}

export interface Standing {
  _links: StandingLinks;
  position: number;
  teamName: string;
  crestURI: string;
  playedGames: number;
  points: number;
  goals: number;
  goalsAgainst: number;
  goalDifference: number;
  wins: number;
  draws: number;
  losses: number;
  home: Away;
  away: Away;
}

export interface StandingLinks {
  team: Competition;
}

export interface Away {
  goals: number;
  goalsAgainst: number;
  wins: number;
  draws: number;
  losses: number;
}

// Converts JSON strings to/from your types
export namespace Convert {
  export function toLeagueTableModel(json: string): LeagueTableModel['standing'] {
    return JSON.parse(json);
  }

  export function leagueTableModelToJson(value: LeagueTableModel): string {
    return JSON.stringify(value, null, 2);
  }
}
