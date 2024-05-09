import type { PageServerLoad } from './$types';
import type { Package } from '$lib/Types/types';
import { apiConfig } from '../../config/api';
import { redirect } from '@sveltejs/kit';

const { baseUrl } = apiConfig;

export const load: PageServerLoad = async ({ params, locals }) => {
	if (locals.user) {
		redirect(302, '/');
	}
	const planoId = params.planoId;

	const response = await fetch(`${baseUrl}/pacote/${planoId}`);
	const pacote: Package = await response.json();

	return { pacote };
};
