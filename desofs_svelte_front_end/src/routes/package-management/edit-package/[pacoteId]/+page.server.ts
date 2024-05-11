import { redirect } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';
import { sendRequest } from '$lib/scripts';
import type { Package, TipoPacote } from '$lib/Types/types';

export const load: PageServerLoad = async ({ params, locals }) => {
	if (!(locals.user && (locals.user.isDocumentManager || locals.user.isAdmin))) {
		redirect(302, '/');
	}

	const pacoteId = params.pacoteId;
	const response = await sendRequest(`pacote/get/${pacoteId}`, 'GET', '', locals.user.token);
	const pacote: Package = await response.json();

	const response2 = await sendRequest(`tipoPacote/list`, 'GET', '', locals.user.token);
	const tipoPacotes: TipoPacote[] = await response2.json();

	return { pacote, tipoPacotes };
};
