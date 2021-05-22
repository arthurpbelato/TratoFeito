export class OfertaModel {
    id: number;
    idItemAlvo: number;
    idUsuarioOfertante: number;
    idItensOfertados: number[];

    constructor(
        id?: number,
        idItemAlvo?: number,
        idUsuarioOfertante?: number,
        idItensOfertados?: number[],
    ) {
        this.id = id;
        this.idItemAlvo = idItemAlvo;
        this.idUsuarioOfertante = idUsuarioOfertante;
        this.idItensOfertados = idItensOfertados;
    }
}