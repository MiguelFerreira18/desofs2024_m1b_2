import type { PageServerLoad } from './$types';
import type { Package, Review } from '$lib/Types/types';
import { sendRequest } from '$lib/scripts';

export const load: PageServerLoad = async ({ params, locals }) => {
	let user = null;

	if (locals.user != undefined) {
		user = await getUser(locals.user.userId, locals.user.token).catch((err) => console.error(err));
	}
	const planosId = params.planoId;

	const response = await sendRequest(`pacote/get/${planosId}`, 'GET', '', '');

	const responseReviews = await sendRequest(`review/pacote/${planosId}`, 'GET', '', '');
	const pacote: Package = await response.json();
	const reviews: Review[] = await responseReviews.json();
	reviews.length = 4;

	return { pacote, reviews, user };
};

const getUser = async (id: number, token: string) => {
	if (!id) return null;
	const userResponse = await sendRequest(`user/info/${id}`, 'GET', '', token);

	let user = null;
	if (!userResponse.ok) {
		user = null;
	} else {
		user = await userResponse.json();
	}

	return user;
};
