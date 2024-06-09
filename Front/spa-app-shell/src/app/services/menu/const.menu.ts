import {SidebarInterface} from "../../interfaces/sidebar.interface";

export const sidebar: SidebarInterface[] = [
    {
        id: '1',
        icon: 'flight',
        title: 'Administración de Vuelos',
        path: 'flight-booking',
        role: ['user','administrator']
    },
    {
        id: '2',
        icon: 'person',
        title: 'Administración de Usuarios',
        path: 'person',
        role: ['administrator']
    },
    {
        id: '3',
        icon: 'payments',
        title: 'Administración de Pagos',
        path: 'flight-booking',
        role: ['user','administrator']
    }
];
