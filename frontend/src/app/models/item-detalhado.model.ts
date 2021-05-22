export class ItemModel {
    id?: number;
    nome?: string;
    descricao?: string;
    foto?: any;
    disponibilidade?: boolean;
    categoria?: string;
    usuario?: String;
    situacao?: string;

    constructor(id?: number,
        nome?: string,
        descricao?: string,
        foto?: any,
        disponibilidade?: boolean,
        categoria?: string,
        usuario?: String,
        situacao?: string,) {

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.foto = foto;
        this.disponibilidade = disponibilidade;
        this.categoria = categoria;
        this.usuario = usuario;
        this.situacao = situacao;
    }

}