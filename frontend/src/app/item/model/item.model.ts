export class ItemModel {
    constructor(
            public id?: number,
            public nome?: string,
            public descricao?: string,
            public foto?: Blob,
            public disponibilidade?: boolean,
            public situacao?: string,
            public idUsuario?: number,
            public idCategoria?: number
        ){
    }
}