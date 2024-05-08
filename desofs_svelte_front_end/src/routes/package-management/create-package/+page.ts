import { apiConfig } from '../../config/api';
import type { PageLoad } from './$types';
import type { TipoPacote } from '$lib/Types/types';

const { baseUrl } = apiConfig;

export const load: PageLoad = async () => {
	const response = await fetch(`${baseUrl}/tipoPacote/list`);
	const tipoPacotes: TipoPacote[] = await response.json();

	return { tipoPacotes };
}
