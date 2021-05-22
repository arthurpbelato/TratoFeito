import { ItemModel } from "./item-detalhado.model";

export class OfertaModel {
    id: number;
    nomeItemAlvo: string;
    categoriaItemAlvo: string;
    nomeUsuarioOfertante: string;
    itensOfertados: ItemModel[];

    constructor(
        id?: number,
        nomeItemAlvo?: string,
        categoriaItemAlvo?: string,
        nomeUsuarioOfertante?: string,
        itensOfertados?: ItemModel[],
    ) {
        this.id = id;
        this.nomeItemAlvo = nomeItemAlvo;
        this.categoriaItemAlvo = categoriaItemAlvo;
        this.nomeUsuarioOfertante = nomeUsuarioOfertante;
        this.itensOfertados = itensOfertados;
    }
}