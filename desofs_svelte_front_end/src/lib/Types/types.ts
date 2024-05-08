// Type
type Package = {
	pacoteId: number;
	nome: string;
	pacoteDescription: string;
	pacoteBasePrice: number;
	disabled: boolean;
	tipoPacote: TipoPacote;
};

type User = {
    userId: number;
    email: string;
    token: string;
    isAdmin: boolean;
    isDocumentManager: boolean;
};

type UserInfo = {
	fullName: string;
	username: string;
	morada: string;
	nif: string;
}

type TipoPacote = {
	tipoPacoteId: number;
	nome: string;
};

type PackageDTOSend = {
	nome: string;
	pacoteDescription: string;
	pacoteBasePrice: number;
	disabled: boolean;
	tipoPacote: number;
};

//Reviews

type Review = {
	reviewId: number;
	reviewText: string;
	rating: number;
	pacote: Package;
};

type ReviewDTOSend = {
	reviewText: string;
	rating: number;
	user: number;
	pacote: number;
};

// Export
export type { Package, TipoPacote, PackageDTOSend, Review, ReviewDTOSend, User, UserInfo };
