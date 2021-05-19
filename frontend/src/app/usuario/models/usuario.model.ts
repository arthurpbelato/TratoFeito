export class UsuarioModel{
    id?: number;
    nome?: string;
    cpf?: string;
    dataNascimento?: Date;
    
    constructor (
        id?: number,
        nome?: string,
        cpf?: string,
        dataNascimento?: Date
    ){}
}