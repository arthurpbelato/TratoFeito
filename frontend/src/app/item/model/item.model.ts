export class ItemModel {
    
    id?: number;
    nome?: string;
    descricao?: string;
    foto?: any;
    disponibilidade?: boolean;
    situacao?: string;
    idUsuario?: number;
    idCategoria?: number;
    
    constructor(
            id?: number,
            nome?: string,
            descricao?: string,
            foto?: any,
            disponibilidade?: boolean,
            situacao?: string,
            idUsuario?: number,
            idCategoria?: number
        ){
    }

}