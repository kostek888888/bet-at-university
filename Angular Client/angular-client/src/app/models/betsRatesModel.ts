export interface BetsRatesModel {
  id: number;
  homeTeamWinRate: number;
  awayTeamWinRate: number;
  drawRate: number;
  homeTeamWinOrDrawRate: number;
  awayTeamWinOrDrawRate: number;
  homeWinOrAwayWin: number;
}

// Converts JSON strings to/from your types
export namespace ConvertBetsRateModel {
  export function toBetsRatesModel(json: string): BetsRatesModel[] {
    return JSON.parse(json);
  }

  export function betsRatesModelToJson(value: BetsRatesModel[]): string {
    return JSON.stringify(value, null, 2);
  }
}
