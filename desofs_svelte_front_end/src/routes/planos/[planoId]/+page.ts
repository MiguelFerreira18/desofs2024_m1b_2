import { apiConfig } from '../../config/api';
import type { PageLoad } from './$types';
import type { Package } from '$lib/Types/types';

const { baseUrl } = apiConfig;

export const load = (async ({ params }) => {
	const planosId = params.planoId;

	const response = await fetch(`${baseUrl}/pacote/${planosId}`);
	const pacote: Package = await response.json();

	return { pacote };
}) satisfies PageLoad;
