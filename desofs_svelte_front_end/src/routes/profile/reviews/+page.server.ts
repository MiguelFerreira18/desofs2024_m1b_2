import { fail, redirect } from '@sveltejs/kit';
import type { PageServerLoad } from '../$types';
import { apiConfig } from '../../config/api';
import type { Review, User } from '$lib/Types/types';

const { baseUrl } = apiConfig;

export const load: PageServerLoad = async ({ locals }) => {
	if (!locals.user) {
		redirect(302, '/');
	}

	const response = await fetch(`${baseUrl}/user/info/` + locals.user.userId, {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
			Accept: '*/*'
		}
	});

	if (!response.ok) {
		fail(400, { invalid: true });
	}

	const perfil: User = await response.json();
	console.log(locals.user.userId);

	const getReviews = await fetch(`${baseUrl}/review/user/${locals.user.userId}`, {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
			Accept: '*/*'
		}
	});

	const reviews: Review[] = await getReviews.json();

	console.log(reviews);

	return { perfil, reviews };
};
