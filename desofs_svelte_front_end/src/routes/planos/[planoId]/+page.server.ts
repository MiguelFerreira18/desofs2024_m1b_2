import { apiConfig } from '../../config/api';
import type { PageServerLoad } from './$types';
import type { Package, Review } from '$lib/Types/types';

const { baseUrl } = apiConfig;

export const load: PageServerLoad = async ({ params, locals }) => {
	let user = null;

	if (locals.user != undefined) {
		user = await getUser(locals.user.userId).catch((err) => console.log(err));
	}
	const planosId = params.planoId;

	const response = await fetch(`${baseUrl}/pacote/${planosId}`);
	const responseReviews = await fetch(`${baseUrl}/review/pacote/${planosId}`);
	const pacote: Package = await response.json();
	const reviews: Review[] = await responseReviews.json();
	reviews.length = 4;

	return { pacote, reviews, user };
};

const getUser = async (id: number) => {
	if (!id) return null;
	const userResponse = await fetch(`${baseUrl}/user/info/${id}`, {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
			Accept: '*/*'
		}
	});
	let user = null;
	if (!userResponse.ok) {
		user = null;
	} else {
		user = await userResponse.json();
	}

	return user;
};
