// See https://kit.svelte.dev/docs/types#app
// for information about these interfaces
declare global {
	namespace App {
		// interface Error {}
		// interface Locals {}
		// interface PageData {}
		// interface PageState {}
		// interface Platform {}
	}
}

declare namespace App {
	interface Locals {
		user: {
            userId: number;
			email: string;
			token: string;
			isAdmin: boolean;
			isDocumentManager: boolean;
		}
	}
}

export {};
