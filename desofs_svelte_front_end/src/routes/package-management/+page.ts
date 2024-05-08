import type { PageLoad } from './$types';
import { apiConfig } from '../config/api';
import type { Package } from '$lib/Types/types';

const { baseUrl } = apiConfig;

export const load: PageLoad = async () => {

	const response = await fetch(`${baseUrl}/pacote/all`);
	const packages: Package[] = await response.json();

	return { packages };
}
