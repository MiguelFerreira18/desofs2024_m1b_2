import { writable } from 'svelte/store';

export const loggedIn = writable(false);
export const isAdmin = writable(false);
export const isDocumentManager = writable(false);