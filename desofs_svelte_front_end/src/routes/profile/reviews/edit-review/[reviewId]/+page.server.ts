import { redirect } from '@sveltejs/kit';
import type { PageServerLoad } from '../../$types';
import type { Package, User, Review } from '$lib/Types/types';
import { sendRequest } from '$lib/scripts';

export const load: PageServerLoad = async ({ params, locals }) => {
	if (!locals.user) {
		redirect(302, '/');
	}

	const { reviewId } = params as { reviewId?: string };

	const getReview = await sendRequest(`review/get/${reviewId}`, 'GET', '', locals.user.token);
	const review: Review = await getReview.json();

	const getPacote = await sendRequest(`pacote/get/${review.pacote.pacoteId}`, 'GET', '', '');
	const pacote: Package = await getPacote.json();

	const user: User = locals.user;
	return { review, pacote, user };
};
