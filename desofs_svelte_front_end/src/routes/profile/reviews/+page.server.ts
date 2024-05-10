import { fail, redirect } from '@sveltejs/kit';
import type { PageServerLoad } from '../$types';
import type { Review, User } from '$lib/Types/types';
import { sendRequest } from '$lib/scripts';

export const load: PageServerLoad = async ({ locals }) => {
	if (!locals.user) {
		redirect(302, '/');
	}

	const response = await sendRequest(
		`user/info/${locals.user.userId}`,
		'GET',
		'',
		locals.user.token
	);

	if (!response.ok) {
		fail(400, { invalid: true });
	}

	const perfil: User = await response.json();

	const getReviews = await sendRequest(
		`review/user/${locals.user.userId}`,
		'GET',
		'',
		locals.user.token
	);
	const reviews: Review[] = await getReviews.json();

	return { perfil, reviews };
};
