import type { PageServerLoad } from './$types';
import type { Package, Delivery } from '$lib/Types/types';
import { sendRequest } from '$lib/scripts';

export const load: PageServerLoad = async ({params,locals}) => {
	
    //const encomendaId = params.encomendaId;

	const responsePacotes = await sendRequest('pacote/all', 'GET', '','');
	const pacotes: Package[] = await responsePacotes.json();

    //const responseEncomenda = await sendRequest(`pacote/${encomendaId}`, 'GET', '', locals.user.token);
    //const encomendas: Delivery[] = await responseEncomenda.json();
	return {pacotes};
};