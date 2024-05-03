// Type
type Package = {
    "pacoteId": number,
    "nome": string,
    "pacoteDescription": string,
    "pacoteBasePrice": number
    "disabled": boolean
    "tipoPacote": TipoPacote
}

type TipoPacote = {
    "tipoPacoteId": number,
    "nome": string
}

type PackageDTOSend = {
    "nome": string,
    "pacoteDescription": string,
    "pacoteBasePrice": number
    "disabled": boolean
    "tipoPacoteId": number
}


// Export
export type {Package, TipoPacote,PackageDTOSend};