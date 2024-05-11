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
};

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
type PackageDTOPatchSend = {
	pacoteId: number;
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

type ReviewDTOSaveSend = {
	reviewText: string;
	rating: number;
	user: number;
	pacote: number;
};
type ReviewDTOPatchSend = {
	reviewId: number;
	reviewText: string;
	rating: number;
	user: number;
	pacote: number;
};

// Encomenda
type DeliveryDTOSend = {
	mealsPerWeek: number;
	numberOfPeople: number;
	price: number;
	pacoteId: number;
	dataEncomenda: string;
	estado: string;
	userId: number;
};

type Delivery = {
	encomendaId: number;
	mealsPerWeek: number;
	numberOfPeople: number;
	price: number;
	pacote: Package;
	dataEncomenda: string;
	estado: string;
	user: User;
};


// Export
export type {
	Package,
	TipoPacote,
	PackageDTOSend,
	PackageDTOPatchSend,
	Review,
	ReviewDTOSaveSend,
	ReviewDTOPatchSend,
	User,
	UserInfo,
	DeliveryDTOSend,
	Delivery
};
