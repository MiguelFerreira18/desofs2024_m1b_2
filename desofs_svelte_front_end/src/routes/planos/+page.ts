import type { Package } from '$lib/Types/types';
import { apiConfig } from '../config/api';
import type { PageLoad } from './$types';
const { baseUrl } = apiConfig;

export const load = (async (LoadEvent) => {
	const { fetch } = LoadEvent;

	const response = await fetch(`${baseUrl}/pacote/all`);
	const packages: Package[] = await response.json();

	const enabledPackages: Package[] = packages.filter((pkg) => pkg.disabled === false);

	return { enabledPackages };
}) satisfies PageLoad;
