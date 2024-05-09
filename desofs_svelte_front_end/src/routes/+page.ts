import type { Package, Review } from '$lib/Types/types';
import type { PageLoad } from './$types';
import { apiConfig } from './config/api';
const { baseUrl } = apiConfig;

export const load : PageLoad = async () => {
	const response = await fetch(`${baseUrl}/pacote/all`);
	const responseReviews = await fetch(`${baseUrl}/review/all`);
	const reviews: Review[] = await responseReviews.json();
	const packages: Package[] = await response.json();
	let enabledPackages: Package[] = [];
	if (packages.length !== 0 && packages.length >= 4) {
		enabledPackages = packages.filter((pkg) => pkg.disabled === false);
		//only send four packages
		enabledPackages.length = 4;
	}
	if(reviews.length !== 0 && reviews.length >= 5){
	//Do reviews request here to
	const enabledPackages: Package[] = packages.filter((pkg) => pkg.disabled === false);
	//only send four packages
	// enabledPackages.length = 4;
    
	return { enabledPackages, reviews };
};
