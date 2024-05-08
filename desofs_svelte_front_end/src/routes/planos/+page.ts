import type { Package } from '$lib/Types/types';
import { apiConfig } from '../config/api';
import type { PageLoad } from './$types';
const { baseUrl } = apiConfig;

export const load: PageLoad = async ({locals}) => {
	if (locals.user) {
		redirect(302, '/');
	}

	const response = await fetch(`${baseUrl}/pacote/all`);
	const packages: Package[] = await response.json();

	const enabledPackages: Package[] = packages.filter((pkg) => pkg.disabled === false);

	return { enabledPackages };
}
