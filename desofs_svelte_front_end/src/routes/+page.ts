import type { Package, Review } from '$lib/Types/types';
import type { PageLoad } from './$types';
import { apiConfig } from './config/api';
const { baseUrl } = apiConfig;

export const load = (async () => {
	const response = await fetch(`${baseUrl}/pacote/all`);
	const responseReviews = await fetch(`${baseUrl}/review/all`);
	const reviews: Review[] = await responseReviews.json();
	const packages: Package[] = await response.json();
	const enabledPackages: Package[] =[]
	if (packages.length !== 0 && packages.length > 4) {
		const enabledPackages: Package[] = packages.filter((pkg) => pkg.disabled === false);
		//only send four packages
		enabledPackages.length = 4;
	
	}
	if(reviews.length !== 0 && reviews.length > 5){
	//Do reviews request here to
	reviews.length = 5;
	}
	

	return { enabledPackages, reviews };
}) satisfies PageLoad;
