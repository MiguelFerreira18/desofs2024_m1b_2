import type { Package } from '$lib/Types/types';
import { redirect } from '@sveltejs/kit'
import { apiConfig } from '../config/api';
import type { PageServerLoad } from './$types';
const { baseUrl } = apiConfig;

export const load: PageServerLoad = async ({locals}) => {
	if (locals.user) {
		redirect(302, '/');
	}

	const response = await fetch(`${baseUrl}/pacote/all`);
	const packages: Package[] = await response.json();

	const enabledPackages: Package[] = packages.filter((pkg) => pkg.disabled === false);

	return { enabledPackages };
}
