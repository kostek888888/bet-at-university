export interface User {
  name?: string;
  surname?: string;
  birthDate?: Date;
  address?: string;
  postCode?: string;
  city?: string;
}

export interface UserStatisticModel {
  id: number;
  wonMatches: number;
  lostMatches: number;
  biggestWin: number;
  biggestWinDate: string;
  accountBalance: number;
}

// Converts JSON strings to/from your types
export namespace Convert {
  export function toUserStatisticModel(json: string): UserStatisticModel {
    return JSON.parse(json);
  }

  export function userStatisticModelToJson(value: UserStatisticModel): string {
    return JSON.stringify(value, null, 2);
  }
}

