import type { PageServerLoad } from './$types';
import type { Package, Recipe,RecipeType } from '$lib/Types/types';
import { sendRequest } from '$lib/scripts';

export const load: PageServerLoad = async ({ params, locals }) => {
	const recipeIdId = params.recipeId;

	const responsePacotes = await sendRequest('pacote/all', 'GET', '', '');
	const pacotes: Package[] = await responsePacotes.json();
	
    const responseTipoReceitas = await sendRequest('tipoReceita/list', 'GET', '', locals.user.token);
    const tipoReceitas: RecipeType[] = await responseTipoReceitas.json();

	const responseEncomenda = await sendRequest(
		`receita/get/${recipeIdId}`,
		'GET',
		'',
		locals.user.token
	);
	const recipe: Recipe = await responseEncomenda.json();

	return { pacotes, recipe, tipoReceitas };
};
