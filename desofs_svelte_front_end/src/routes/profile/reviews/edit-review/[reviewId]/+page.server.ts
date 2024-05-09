import { redirect } from '@sveltejs/kit';
import type { PageServerLoad } from '../../$types';
import { apiConfig } from '../../../../config/api';
import type { Package, User, Review } from '$lib/Types/types';

const { baseUrl } = apiConfig;

export const load: PageServerLoad = async ({ params, locals }) => {
	if (!locals.user) {
		redirect(302, '/');
	}
	const reviewId = params.reviewId;

	const getReview = await fetch(`${baseUrl}/review/${reviewId}`);
	const review: Review = await getReview.json();

	const getPacote = await fetch(`${baseUrl}/pacote/${review.pacote.pacoteId}`);
	const pacote: Package = await getPacote.json();

	console.log(review);

	const user: User = locals.user;
	return { review, pacote, user };
};
