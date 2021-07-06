export interface Contract {
    hotelName: string;
    email: string;
    contactNoList: Array<string>;
    type: string;
    noOfRooms: number;
    noOfAdults: number;
    price: number;
    endDate: Date;
    startDate: Date;
    markup: number;
    address: string;
}
