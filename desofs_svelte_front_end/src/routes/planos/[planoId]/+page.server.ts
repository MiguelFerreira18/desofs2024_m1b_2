import { apiConfig } from '../../config/api';
import { redirect } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';
import type { Package, Review } from '$lib/Types/types';

const { baseUrl } = apiConfig;

export const load: PageServerLoad = async ({ params, locals }) => {
	if (locals.user) {
		redirect(302, '/');
	}
	const planosId = params.planoId;

	const response = await fetch(`${baseUrl}/pacote/${planosId}`);
	const responseReviews = await fetch(`${baseUrl}/review/pacote/${planosId}`);
	const pacote: Package = await response.json();
	const reviews: Review[] = await responseReviews.json();
	reviews.length = 4;

	return { pacote, reviews };
};
