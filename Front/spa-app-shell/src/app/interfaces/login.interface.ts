export interface LoginInterface {
    name?: string;
    userName?: string;
    nickname?: string;
    roles?: string[];
}

export interface LoginResponseInterface {
    userId: string;
}
