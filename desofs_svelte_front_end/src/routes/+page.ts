import { sendRequest } from '$lib/scripts';
import type { Package, Review } from '$lib/Types/types';
import type { PageLoad } from './$types';

export const load: PageLoad = async () => {
	const response = await sendRequest('pacote/all', 'GET', '', '');
	const responseReviews = await sendRequest('review/all', 'GET', '', '');
	const reviews: Review[] = await responseReviews.json();
	const packages: Package[] = await response.json();
	let enabledPackages: Package[] = [];
	if (packages.length !== 0 && packages.length >= 4) {
		enabledPackages = packages.filter((pkg) => pkg.disabled === false);
		//only send four packages
		enabledPackages.length = 4;
	}
	if (reviews.length !== 0 && reviews.length >= 5) {
		const enabledPackages: Package[] = packages.filter((pkg) => pkg.disabled === false);

		return { enabledPackages, reviews };
	}
};
