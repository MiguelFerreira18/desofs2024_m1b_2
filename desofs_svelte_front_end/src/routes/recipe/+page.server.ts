import type { PageServerLoad } from './$types';
import type { Recipe } from '$lib/Types/types';
import { sendRequest } from '$lib/scripts';

export const load: PageServerLoad = async ({ locals }) => {
	const response = await sendRequest(`receita/all`, 'GET', '', locals.user.token);

	const recipes: Recipe[] = await response.json();
	return { recipes };
};
