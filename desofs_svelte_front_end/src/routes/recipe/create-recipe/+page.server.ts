import type { PageServerLoad } from './$types';
import type { Package, RecipeType } from '$lib/Types/types';
import { sendRequest } from '$lib/scripts';

export const load: PageServerLoad = async ({ locals }) => {
	const responsePacotes = await sendRequest('pacote/all', 'GET', '', '');
	const pacotes: Package[] = await responsePacotes.json();

	const responseTipoReceitas = await sendRequest('tipoReceita/list', 'GET', '', locals.user.token);
	const tipoReceitas: RecipeType[] = await responseTipoReceitas.json();

	return { pacotes, tipoReceitas };
};
