import type { PageLoad } from './$types';
import type { Package } from '$lib/Types/types';
import { apiConfig } from '../../config/api';

const { baseUrl } = apiConfig;

export const load = (async ({params}) => {

    const planoId = params.planoId;

	const response = await fetch(`${baseUrl}/pacote/${planoId}`);
	const pacote: Package = await response.json();


    return {pacote};
}) satisfies PageLoad;