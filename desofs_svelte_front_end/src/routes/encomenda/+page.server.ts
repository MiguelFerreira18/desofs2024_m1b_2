import type { PageServerLoad } from './$types';
import type { Delivery } from '$lib/Types/types';
import { sendRequest } from '$lib/scripts';

export const load: PageServerLoad = async ({ locals }) => {
	console.log('locals', locals);
	
	const response = await sendRequest(
		`encomenda/all/${locals.user.userId}`,
		'GET',
		'',
		locals.user.token
	);
	const encomendas: Delivery[] = await response.json();
	return { encomendas };
};
