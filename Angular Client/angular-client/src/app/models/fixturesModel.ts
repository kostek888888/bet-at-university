// To parse this data:
//
//   import { Convert, LeagueTableModel } from "./file";
//
//   const leagueTableModel = Convert.toLeagueTableModel(json);

export interface FixturesArray {
  _links: LeagueTableModelLinks;
  count: number;
  fixtures: Fixture[];
}

export interface LeagueTableModelLinks {
  self: Competition;
  competition: Competition;
}

export interface Competition {
  href: string;
}

export interface Fixture {
  _links: FixtureLinks;
  date: string;
  status: Status;
  matchday: number;
  homeTeamName: string;
  awayTeamName: string;
  result: Result;
  odds: null;
}

export interface FixtureLinks {
  self: Competition;
  competition: Competition;
  homeTeam: Competition;
  awayTeam: Competition;
}

export interface Result {
  goalsHomeTeam: number;
  goalsAwayTeam: number;
  halfTime: HalfTime;
}

export interface HalfTime {
  goalsHomeTeam: number;
  goalsAwayTeam: number;
}

export enum Status {
  Finished = 'FINISHED',
  Timed = 'TIMED',
  Scheduled = 'SCHEDULTED'
}

// Converts JSON strings to/from your types
export namespace Convert {
  export function toFixtures(json: string): FixturesArray['fixtures'] {
    return JSON.parse(json);
  }

  export function fixturesToJson(value: FixturesArray['fixtures']): string {
    return JSON.stringify(value, null, 2);
  }
}
