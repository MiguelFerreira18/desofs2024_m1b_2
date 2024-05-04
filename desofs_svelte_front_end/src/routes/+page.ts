import type { Package, Review } from '$lib/Types/types';
import type { PageLoad } from './$types';
import { apiConfig } from './config/api';
const { baseUrl } = apiConfig;

export const load = (async () => {
	const response = await fetch(`${baseUrl}/pacote/all`);
	const responseReviews = await fetch(`${baseUrl}/review/all`);
	const reviews: Review[] = await responseReviews.json();
	const packages: Package[] = await response.json();


	//Do reviews request here to
	const enabledPackages: Package[] = packages.filter((pkg) => pkg.disabled === false);
	//only send four packages
	enabledPackages.length = 4
	reviews.length = 5

	

	return { enabledPackages,reviews };
}) satisfies PageLoad;
