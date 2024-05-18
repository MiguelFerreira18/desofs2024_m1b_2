import { redirect } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';
import { sendRequest } from '$lib/scripts';
import type { TipoPacote } from '$lib/Types/types';

export const load: PageServerLoad = async ({ locals }) => {
	if (!(locals.user && (locals.user.isDocumentManager || locals.user.isAdmin))) {
		redirect(302, '/');
	}

	const response = await sendRequest(`tipoPacote/list`, 'GET', '', locals.user.token);
	const tipoPacotes: TipoPacote[] = await response.json();

	return { tipoPacotes };
};
