import { apiConfig } from '../../config/api';
import type { PageLoad } from './$types';
import type { Package, Review } from '$lib/Types/types';

const { baseUrl } = apiConfig;

export const load = (async ({ params }) => {
	const planosId = params.planoId;

	const response = await fetch(`${baseUrl}/pacote/${planosId}`);
	const responseReviews = await fetch(`${baseUrl}/review/pacote/${planosId}`);
	const pacote: Package = await response.json();
	const reviews: Review[] = await responseReviews.json();
	reviews.length = 4;

	return { pacote, reviews };
}) satisfies PageLoad;
