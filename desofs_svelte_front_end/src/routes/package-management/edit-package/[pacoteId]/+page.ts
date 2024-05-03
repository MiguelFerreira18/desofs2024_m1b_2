import type { PageLoad } from './$types';
import { apiConfig } from '../../../config/api';
import type { Package, TipoPacote } from '$lib/Types/types';

const { baseUrl } = apiConfig;

export const load = (async ({ params }) => {

	const pacoteId = params.pacoteId;
	const response = await fetch(`${baseUrl}/pacote/${pacoteId}`);
	const pacote:Package = await response.json();

    const response2 = await fetch(`${baseUrl}/tipoPacote/list`);
    const tipoPacotes: TipoPacote[] = await response2.json();

	return {pacote,tipoPacotes};
}) satisfies PageLoad;
