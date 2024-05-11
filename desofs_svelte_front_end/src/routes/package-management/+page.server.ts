import { redirect } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';
import type { Package } from '$lib/Types/types';
import { sendRequest } from '$lib/scripts';

export const load: PageServerLoad = async ({ locals }) => {
	if (!(locals.user && (locals.user.isDocumentManager || locals.user.isAdmin))) {
		redirect(302, '/');
	}

	const response = await sendRequest('pacote/all', 'GET', '', '');
	const packages: Package[] = await response.json();

	return { packages };
};
