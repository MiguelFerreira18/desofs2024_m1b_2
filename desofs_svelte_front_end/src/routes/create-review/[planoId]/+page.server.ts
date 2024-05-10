import type { PageServerLoad } from './$types';
import type { Package, User } from '$lib/Types/types';
import { redirect } from '@sveltejs/kit';
import { sendRequest } from '$lib/scripts';

export const load: PageServerLoad = async ({ params, locals }) => {
	if (!locals.user) {
		redirect(302, '/');
	}
	const planoId = params.planoId;

	const response = await sendRequest(`pacote/get/${planoId}`, 'GET', '', '');
	const pacote: Package = await response.json();
	const user: User = locals.user;
	return { pacote, user };
};
