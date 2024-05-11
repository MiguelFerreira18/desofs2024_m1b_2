import type { PageServerLoad } from './$types';
import type { Package } from '$lib/Types/types';
import { sendRequest } from '$lib/scripts';

export const load: PageServerLoad = async () => {
	
	const responsePacotes = await sendRequest('pacote/all', 'GET', '','');
	const pacotes: Package[] = await responsePacotes.json();

	return {pacotes};
};