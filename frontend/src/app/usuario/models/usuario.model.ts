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
    ){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }
}