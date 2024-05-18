import { sendRequest } from '$lib/scripts';
import type { Package } from '$lib/Types/types';
import type { PageServerLoad } from './$types';

export const load: PageServerLoad = async () => {
	const response = await sendRequest('pacote/all', 'GET', '', '');
	const packages: Package[] = await response.json();

	const enabledPackages: Package[] = packages.filter((pkg) => pkg.disabled === false);

	return { enabledPackages };
};
